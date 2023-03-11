package main.java.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import main.java.enums.Gender;

import java.util.ArrayList;

public class Customer extends User{
    public Customer(String name, String email, int age, Gender gender, int[] location, UserProfile userProfile) {
        super(name, email, age, gender, location, userProfile);
    }
}
