package main.java.models;

import lombok.*;
import main.java.enums.Gender;

import java.util.ArrayList;
import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    private String name;
    private String email;
    private int age;
    private Gender gender;
    private int[] location;

    private UserProfile userProfile;
    private ArrayList<MatchRequest> matchRecordsArrayList = new ArrayList<>();


    private ArrayList<User> followUsers = new ArrayList<>();

    public User(String name, String email, int age, Gender gender, int[] location, UserProfile userProfile) {
        this.age= age;
        this.name = name;
        this.gender = gender;
        this.location = location;
        this.userProfile = userProfile;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Data
    @RequiredArgsConstructor
    public static class UserProfile {
        String photos[];
        String bio;

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", location=" + Arrays.toString(location) +
                ", userProfile=" + userProfile +
                ", followUsers=" + followUsers +
                '}';
    }
}
