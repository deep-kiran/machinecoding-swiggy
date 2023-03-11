package main.java.models;

import main.java.enums.Gender;

import java.util.ArrayList;

public class Admin extends User{
    Admin(String name, String email, int age, Gender gender, int[] location, User.UserProfile userProfile) {
        super(name, email, age, gender, location, userProfile);
    }
}
