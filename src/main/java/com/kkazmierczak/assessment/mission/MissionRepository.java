package com.kkazmierczak.assessment.mission;

import com.kkazmierczak.assessment.rocket.Rocket;
import com.kkazmierczak.assessment.rocket.RocketStatus;

import java.util.List;
import java.util.Set;

interface MissionRepository {
    default List<Mission> findAll() {
        return List.of(
                new Mission(95832095L,
                        "Mars",
                        MissionStatus.SCHEDULED,
                        Set.of()),
                new Mission(960238460982L,
                        "Luna1",
                        MissionStatus.PENDING,
                        Set.of(new Rocket(6238683029L,
                                        "Dragon 1",
                                        RocketStatus.ON_GROUND,
                                        true),
                                new Rocket(53129050212L,
                                        "Dragon 2",
                                        RocketStatus.ON_GROUND,
                                        true))
                ),
                new Mission(629034690423L,
                        "Double Landing",
                        MissionStatus.ENDED,
                        Set.of()),
                new Mission(69023682351L,
                        "Transit",
                        MissionStatus.IN_PROGRESS,
                        Set.of(new Rocket(23859523L,
                                        "Red Dragon",
                                        RocketStatus.ON_GROUND,
                                        true),
                                new Rocket(590320532L,
                                        "Dragon XL",
                                        RocketStatus.IN_SPACE,
                                        true),
                                new Rocket(6041234128542L,
                                        "Falcon Heavy",
                                        RocketStatus.IN_SPACE,
                                        true))
                ),
                new Mission(6329602386124L,
                        "Luna2",
                        MissionStatus.SCHEDULED,
                        Set.of()),
                new Mission(1258912358923L,
                        "Vertical Landing",
                        MissionStatus.ENDED,
                        Set.of())
        );
    }

    default boolean isExistingId(long missionId){
        return findAll()
                .stream()
                .anyMatch(mission -> mission.getMissionId() == missionId);
    }
}
