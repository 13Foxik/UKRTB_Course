package com.KKS.ukrtb_course;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.KKS.ukrtb_course.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class Profile extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private String uid;
    private ImageView imageViewAvatar;
    private ActivityProfileBinding binding;
    private DatabaseReference mDataBase;
    private DatabaseReference uidRef;
    private StorageReference mStorageRef;
    private StorageReference mStorageRefDel;
    private Uri uploadUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Init();

        // для смены аватарки нужно тыкнуть на аватарку
        imageViewAvatar.setOnClickListener(v -> selectImageFromGallery());

        //Выставляет поля из БД
        setDates();

    }

    private void Init(){
        imageViewAvatar = findViewById(R.id.imageViewAvatar);
        mDataBase = FirebaseDatabase.getInstance().getReference();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        uidRef = mDataBase.child("Users/").child(uid);
        mStorageRef = FirebaseStorage.getInstance().getReference("ImageAvatars");

    }
    private void setDates(){
        ValueEventListener imageListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user != null) {
                        binding.NickName.setText(user.username);
                        binding.edLogin.setText(user.email);
                        if(user.image_id != null){
                            Picasso.get().load(user.image_id).into(imageViewAvatar);
                        }
                    } else {
                        // Логирование или обработка случая, когда user == null
                        Log.e("TAG", "User is null");
                    }
                } else {
                    // Логирование или обработка случая, когда нет данных
                    Log.e("TAG", "Data does not exist");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "Database error: " + databaseError.getMessage());
            }
        };
        Log.e("TAG", "Current User UID: " + uid);
        uidRef.addValueEventListener(imageListener);
    }

    // выбор изобржаения из галереи
    @SuppressLint("IntentReset")
    private void selectImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    // вроде для обработки изображения или чет такое
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            imageViewAvatar.setImageURI(imageUri);
            UploadImage();
        }
    }


    //Загружает в базу данных фотку, а так же создает у пользователя ссылку по которой можно обратится к этой фотке
    //Если фотку меняют, в таком случае она просто перезаписывается
    private void UploadImage(){
        Bitmap bitmap = ((BitmapDrawable) imageViewAvatar.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        final StorageReference mRef = mStorageRef.child(uid + "Avatar");
        UploadTask up = mRef.putBytes(byteArray);
        Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return mRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                uploadUri = task.getResult();
                mDataBase.child("Users").child(uid).child("image_id").setValue(uploadUri.toString());
            }
        });
    }

    public void onClickLogout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Profile.this, MainActivity.class));
    }
}
