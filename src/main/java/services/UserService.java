package main.java.services;

import main.java.dao.Storage;
import main.java.enums.Gender;
import main.java.enums.MatchStatus;
import main.java.models.Customer;
import main.java.models.MatchRequest;
import main.java.models.User;

import java.util.Deque;
import java.util.Map;

public class UserService {
    private Map<String, User> userMap = Storage.getUserMap();
    private Map<String,MatchRequest> matchRequests = Storage.getMatchRequestsDeque();

    public User registerUser(String email, String name, int age, Gender gender, int []location){
        if(userMap.containsKey(email)){
            throw new UnsupportedOperationException("User is already registered");
        }
        User user = new Customer(name, email, age, gender, location, new User.UserProfile());
        userMap.put(user.getEmail(), user);
        return user;
    }

    public boolean deleteUser(String email){
        User user = userMap.get(email);
        user.getMatchRecordsArrayList().forEach(matchRequest -> {
            matchRequest.getFromUser().getFollowUsers().remove(user);
            matchRequest.getToUser().getFollowUsers().remove(user);
            matchRequests.remove(matchRequest.getUuid());
        });
        userMap.remove(email);
        return true;
    }
}
