package com.kkazmierczak.assessment.rocket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RocketServiceImplTest {
    private final RocketService rocketService = new RocketServiceImpl();
    private final long ROCKET_ID = 90532095L;
    private final String ROCKET_NAME = "ROCKET 001";

    @Test
    void createNew() {
        var newRocket = rocketService.createNew(ROCKET_ID, ROCKET_NAME);
        assertEquals(ROCKET_ID, newRocket.getRocketId());
        assertEquals(ROCKET_NAME, newRocket.getName());
        assertEquals(RocketStatus.ON_GROUND, newRocket.getRocketStatus());
    }

    @Test
    void changeStatus() {
        var newRocket = rocketService.createNew(ROCKET_ID, ROCKET_NAME);
        var changedStatusRocket = rocketService.changeStatus(newRocket, RocketStatus.IN_REPAIR);
        assertEquals(ROCKET_ID, changedStatusRocket.getRocketId());
        assertEquals(ROCKET_NAME, changedStatusRocket.getName());
        assertEquals(RocketStatus.IN_REPAIR, changedStatusRocket.getRocketStatus());
    }
}