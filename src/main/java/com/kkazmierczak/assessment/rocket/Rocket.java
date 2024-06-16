package com.kkazmierczak.assessment.rocket;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"rocketId"})
public class Rocket {
    private final long rocketId;
    private final String name;
    private final RocketStatus rocketStatus;
    private boolean isAssigned;

    public Rocket(long rocketId, String name) {
        this.rocketId = rocketId;
        this.name = name;
        this.rocketStatus = RocketStatus.ON_GROUND;
        this.isAssigned = false;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }
}
