package com.kkazmierczak.assessment.mission;

import com.kkazmierczak.assessment.rocket.Rocket;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
class MissionServiceImpl implements MissionService {
    private final MissionRepository missionRepository;

    @Override
    public Mission createNew(long missionId, String name) {
        if(missionRepository.isExistingId(missionId)){
            throw new IllegalArgumentException(String.format("Mission with id %d already exists.", missionId));
        }
        return new Mission(missionId, name, Set.of());
    }

    @Override
    public Mission assignRocket(Mission mission, Rocket rocket) {
        if(isRocketAssignable(mission, rocket)){
            throw new IllegalArgumentException(String.format("Error while trying to assign rocket id %d to mission id %d.", rocket.getRocketId(), mission.getMissionId()));
        }
        rocket.setAssigned(true);
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

    private static boolean isRocketAssignable(Mission mission, Rocket rocket) {
        return rocket.isAssigned() ||
                MissionStatus.ENDED == mission.getMissionStatus();
    }
}
