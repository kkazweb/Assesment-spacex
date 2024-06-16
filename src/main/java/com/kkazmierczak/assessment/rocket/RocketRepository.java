package com.kkazmierczak.assessment.rocket;

import java.util.List;

interface RocketRepository {
    default List<Rocket> findAll() {
        return List.of(
                new Rocket(6238683029L,
                        "Dragon 1",
                        RocketStatus.ON_GROUND,
                        true),
                new Rocket(53129050212L,
                        "Dragon 2",
                        RocketStatus.ON_GROUND,
                        true),
                new Rocket(23859523L,
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
                        true));
    }

    default boolean isExistingId(long rocketId){
        return findAll()
                .stream()
                .anyMatch(rocket -> rocket.getRocketId() == rocketId);
    }
}
