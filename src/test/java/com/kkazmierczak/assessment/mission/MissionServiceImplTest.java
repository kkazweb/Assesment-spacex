package com.kkazmierczak.assessment.mission;

import com.kkazmierczak.assessment.rocket.Rocket;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MissionServiceImplTest {
    private final MissionService missionService = new MissionServiceImpl();
    private final long MISSION_ID = 90532095L;
    private final String MISSION_NAME = "MISSION 342";

    @Test
    void createNew() {
        var newMission = missionService.createNew(MISSION_ID, MISSION_NAME);
        assertEquals(MISSION_ID, newMission.getMissionId());
        assertEquals(MISSION_NAME, newMission.getName());
        assertEquals(MissionStatus.SCHEDULED, newMission.getMissionStatus());
    }

    @Test
    void assignRocket() {
        var rocket = new Rocket(9583295L, "Rocket 1");
        var newMission = missionService.createNew(MISSION_ID, MISSION_NAME);
        var assignedRocketMission = missionService.assignRocket(newMission, rocket);
        assertEquals(MISSION_ID, assignedRocketMission.getMissionId());
        assertEquals(MISSION_NAME, assignedRocketMission.getName());
        assertEquals(Set.of(rocket), assignedRocketMission.getRockets());
    }

    @Test
    void changeStatus() {
        var missionStatus = MissionStatus.IN_PROGRESS;
        var newMission = missionService.createNew(MISSION_ID, MISSION_NAME);
        var changedStatusMission = missionService.changeStatus(newMission, missionStatus);
        assertEquals(MISSION_ID, changedStatusMission.getMissionId());
        assertEquals(MISSION_NAME, changedStatusMission.getName());
        assertEquals(missionStatus, changedStatusMission.getMissionStatus());
    }
}