package main.java.models;

import lombok.*;
import main.java.enums.MatchStatus;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchRequest {
    String uuid = UUID.randomUUID().toString();
    User fromUser;
    User toUser;
    double distanceDifference;
    int ageDifference;

    int genderDifference;
    MatchStatus matchStatus;
}
