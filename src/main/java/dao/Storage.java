package main.java.dao;

import main.java.models.MatchRequest;
import main.java.models.User;

import java.util.*;

public class Storage {
    private static Map<String,User> userMap;

    private static Map<String,MatchRequest> matchRequestsDeque;


    public static Map<String, User> getUserMap() {
        if(Objects.isNull(userMap)){
            userMap = new HashMap<>();
        }
        return userMap;
    }

    public static Map<String,MatchRequest> getMatchRequestsDeque() {
        if(Objects.isNull(matchRequestsDeque)){
            matchRequestsDeque = new HashMap<>();
        }
        return matchRequestsDeque;
    }
}
