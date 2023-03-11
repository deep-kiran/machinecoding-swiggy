package main.java.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.java.DistanceUtility;
import main.java.dao.Storage;
import main.java.enums.MatchStatus;
import main.java.models.MatchRequest;
import main.java.models.User;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MatchService {

    Logger logger = LoggerFactory.getLogger(MatchService.class);
    private static Map<String, MatchRequest> matchRequests = Storage.getMatchRequestsDeque();
    private static Map<String, User> userHashMap = Storage.getUserMap();
    private NotificationService notificationService;
    public MatchService(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public List<MatchRequest> viewMatches(User user){
        if(matchRequests!=null && !matchRequests.isEmpty()) {
            List<MatchRequest> matchRequestsForUser = matchRequests.values().stream()
                    .filter(request -> request.getToUser().equals(user))
                    .sorted(Comparator.comparing(matchRequest -> matchRequest.getAgeDifference() + matchRequest.getDistanceDifference() - matchRequest.getGenderDifference()))
                    .collect(Collectors.toList());
            return matchRequestsForUser;
        }
        return null;
    }
    public List<User> viewTinderFeed(User currentUser){
        return userHashMap.values().stream()
                .filter(user -> !user.getEmail().equals(currentUser.getEmail()))
                .sorted(Comparator.comparing(user -> DistanceUtility.getDifference(currentUser.getLocation(), user.getLocation())
                + Math.abs(currentUser.getAge()- user.getAge() - Math.abs(Math.abs(user.getGender().ordinal()- currentUser.getGender().ordinal()))))
        ).collect(Collectors.toList());
    }

    public boolean sendLikeToUser(User fromUser, User toUser){

        MatchRequest matchRequest = MatchRequest.builder().matchStatus(MatchStatus.LIKED).fromUser(fromUser)
                .toUser(toUser)
                .genderDifference(Math.abs(fromUser.getGender().ordinal()- toUser.getGender().ordinal()))
                .ageDifference(Math.abs(fromUser.getAge()- toUser.getAge()))
                .distanceDifference(DistanceUtility.getDifference(fromUser.getLocation(), toUser.getLocation())).build();
        matchRequests.put(matchRequest.getUuid(), matchRequest);
        fromUser.getMatchRecordsArrayList().add(matchRequest);
        toUser.getMatchRecordsArrayList().add(matchRequest);
        return true;
    }

    public void acceptMatchRequest(User toUser, MatchRequest matchRequest){
        if(!toUser.equals(matchRequest.getToUser()) || !matchRequest.getMatchStatus().equals(MatchStatus.LIKED)){
            throw  new UnsupportedOperationException("This matchRequest doesn't belong to the user");
        }
         matchRequest.setMatchStatus(MatchStatus.MUTUAL_LIKE);
        toUser.getFollowUsers().add(matchRequest.getFromUser());
        matchRequest.getFromUser().getFollowUsers().add(toUser);
        notificationService.notify(matchRequest.getFromUser(), matchRequest.getToUser());
    }
}
