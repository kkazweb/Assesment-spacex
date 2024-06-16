package com.kkazmierczak.assessment.mission;

import com.kkazmierczak.assessment.rocket.Rocket;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = {"missionId"})
class Mission {
    private long missionId;
    private MissionStatus missionStatus;
    private Set<Rocket> rockets;

    public Mission(long missionId) {
        this.missionId = missionId;
        this.missionStatus = MissionStatus.SCHEDULED;
        this.rockets = new HashSet<>();
    }
}
