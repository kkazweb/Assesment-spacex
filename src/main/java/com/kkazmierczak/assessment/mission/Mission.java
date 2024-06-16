package com.kkazmierczak.assessment.mission;

import com.kkazmierczak.assessment.rocket.Rocket;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Set;

@Getter
@EqualsAndHashCode(of = {"missionId"})
public class Mission {
    private long missionId;
    private MissionStatus missionStatus;
    private Set<Rocket> rockets;

    public Mission(long missionId, Set<Rocket> rockets) {
        this.missionId = missionId;
        this.missionStatus = MissionStatus.SCHEDULED;
        this.rockets = rockets;
    }
}
