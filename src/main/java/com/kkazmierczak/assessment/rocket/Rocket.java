package com.kkazmierczak.assessment.rocket;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"rocketId"})
public class Rocket {
    private long rocketId;
    private RocketStatus rocketStatus;

    public Rocket(long rocketId) {
        this.rocketId = rocketId;
        this.rocketStatus = RocketStatus.ON_GROUND;
    }
}
