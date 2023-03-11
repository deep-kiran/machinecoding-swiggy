package test.java;

import main.java.enums.Gender;
import main.java.models.MatchRequest;
import main.java.models.User;
import main.java.services.MatchService;
import main.java.services.NotificationService;
import main.java.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class TinderTest {
    public MatchService matchService;
    public UserService userService;
    public NotificationService notificationService;
    @BeforeEach
    public void setup(){
        notificationService = new NotificationService();
        matchService = new MatchService(notificationService);
        userService = new UserService();
    }

    @Test
    public void test(){
        List<User> users = initializeUsers(userService);
        List<MatchRequest> users1 = matchService.viewMatches(users.get(0));
        Assertions.assertEquals(users1, null);
        List<User> users2 = matchService.viewTinderFeed(users.get(0));
        User john = users2.get(0);
        System.out.println(john);
        matchService.sendLikeToUser(users.get(0),john);
        List<MatchRequest> users3  = matchService.viewMatches(users2.get(0));
        System.out.println("user 3 "+ users3);
        matchService.acceptMatchRequest(users2.get(0), users3.get(0));
        Assertions.assertEquals(john.getFollowUsers().contains(users.get(0)),true);
        Assertions.assertEquals(users.get(0).getFollowUsers().contains(john),true);
        userService.deleteUser(john.getEmail());
        Assertions.assertEquals(users.get(0).getFollowUsers().contains(john),false);
        Assertions.assertEquals(users.get(0).getFollowUsers().size()==0,true);

    }

    public List<User> initializeUsers(UserService userService){
        User user1 = userService.registerUser("deep@gmail.com","deep",21, Gender.FEMALE,new int[]{1,0});
        User user2 = userService.registerUser("john@gmail.com","john",27, Gender.MALE,new int[]{2,0});
        User user3 = userService.registerUser("cena@gmail.com","cena",22, Gender.MALE,new int[]{12,0});
        User user4 = userService.registerUser("rock@gmail.com","rock",19, Gender.FEMALE,new int[]{18,0});
        User user5 = userService.registerUser("bobbb@gmail.com","bobb",18, Gender.MALE,new int[]{17,0});
        User user6 = userService.registerUser("justin@gmail.com","justin",23, Gender.MALE,new int[]{18,0});
        return List.of(user1,user2,user3,user4,user5,user6);
    }
}
