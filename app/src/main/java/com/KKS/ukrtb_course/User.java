package com.KKS.ukrtb_course;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User {

    public String username, email, image_id, birthday;
    public ArrayList<String> accessOfCourses;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, String image_id, String birthday, ArrayList<String> accessOfCourses) {
        this.username = username;
        this.email = email;
        this.image_id = image_id;
        this.birthday = birthday;
        this.accessOfCourses = accessOfCourses;
    }

}