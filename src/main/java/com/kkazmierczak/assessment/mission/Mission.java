package com.kkazmierczak.assessment.mission;

import com.kkazmierczak.assessment.rocket.Rocket;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Set;

@Getter
@EqualsAndHashCode(of = {"missionId"})
@AllArgsConstructor
public class Mission {
    private final long missionId;
    private final String name;
    private MissionStatus missionStatus;
    private Set<Rocket> rockets;

    public Mission(long missionId, String name, Set<Rocket> rockets) {
        this.missionId = missionId;
        this.name = name;
        this.missionStatus = MissionStatus.SCHEDULED;
        this.rockets = rockets;
    }
}
