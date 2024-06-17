package com.kkazmierczak.assessment.mission;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.kkazmierczak.assessment.rocket.Rocket;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Set;

@Getter
@EqualsAndHashCode(of = {"missionId"})
@AllArgsConstructor
public class Mission implements Comparable<Mission>{
    private final long missionId;
    private final String name;
    private final MissionStatus missionStatus;
    private final Set<Rocket> rockets;

    public Mission(long missionId, String name, Set<Rocket> rockets) {
        this.missionId = missionId;
        this.name = name;
        this.missionStatus = MissionStatus.SCHEDULED;
        this.rockets = rockets;
    }

    @Override
    public int compareTo(Mission o){
        return ComparisonChain.start()
                .compare(this.rockets.size(), o.rockets.size(), Ordering.natural().reverse())
                .compare(this.getName(), o.getName(), Ordering.natural().reverse())
                .result();

    }
}
