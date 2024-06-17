package com.kkazmierczak.assessment.mission.assignment;

import com.kkazmierczak.assessment.mission.Mission;
import com.kkazmierczak.assessment.mission.MissionStatus;
import com.kkazmierczak.assessment.rocket.Rocket;

import java.util.stream.Collectors;
import java.util.stream.Stream;

class MissionAssignmentServiceImpl implements MissionAssignmentService {

    @Override
    public Mission assignRocket(Mission mission, Rocket rocket) {
        if(isNotAssignable(mission, rocket)){
            throw new IllegalArgumentException(String.format("Error while trying to assign rocket id %d to mission id %d.", rocket.getRocketId(), mission.getMissionId()));
        }
        rocket.setAssigned(true);
        var rockets = Stream.concat(
                        mission.getRockets().stream(),
                        Stream.of(rocket))
                .collect(Collectors.toSet());
        return new Mission(mission.getMissionId(), mission.getName(), mission.getMissionStatus(), rockets);
    }

    private boolean isNotAssignable(Mission mission, Rocket rocket) {
        return rocket.isAssigned() ||
                MissionStatus.ENDED == mission.getMissionStatus();
    }
}
