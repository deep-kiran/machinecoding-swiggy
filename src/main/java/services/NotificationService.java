package main.java.services;

import main.java.models.User;


public class NotificationService {
    public void notify(User from, User to){
        System.out.println(from.getName() + " : your match request with" + to.getName() + "  has been accepted ");
    }
}
