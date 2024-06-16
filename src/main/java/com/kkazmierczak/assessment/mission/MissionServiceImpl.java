package com.kkazmierczak.assessment.mission;

import com.kkazmierczak.assessment.rocket.Rocket;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MissionServiceImpl implements MissionService {
    @Override
    public Mission createNew(long missionId, String name) {
        return new Mission(missionId, name, Set.of());
    }

    @Override
    public Mission assignRocket(Mission mission, Rocket rocket) {
        var rockets = Stream.concat(
                        mission.getRockets().stream(),
                        Stream.of(rocket))
                .collect(Collectors.toSet());
        return new Mission(mission.getMissionId(), mission.getName(), mission.getMissionStatus(), rockets);
    }

    @Override
    public Mission changeStatus(Mission mission, MissionStatus missionStatus) {
        return new Mission(mission.getMissionId(), mission.getName(), missionStatus, mission.getRockets());
    }
}
