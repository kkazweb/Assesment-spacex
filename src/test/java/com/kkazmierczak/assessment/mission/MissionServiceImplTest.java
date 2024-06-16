package com.kkazmierczak.assessment.mission;

import com.kkazmierczak.assessment.rocket.Rocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MissionServiceImplTest {
    private final MissionService missionService = new MissionServiceImpl();
    private final long MISSION_ID = 90532095L;

    @Test
    void add() {
        var newMission = missionService.createNew(MISSION_ID);
        assertEquals(MISSION_ID, newMission.getMissionId());
        assertEquals(MissionStatus.SCHEDULED, newMission.getMissionStatus());
    }

    @Test
    void assignRocket() {
        var rocket = new Rocket(9583295L, "Rocket 1");
        var newMission = missionService.createNew(MISSION_ID);
        var mission = missionService.assignRocket(newMission, rocket);
        assertEquals(MISSION_ID, mission.getMissionId());
        assertEquals("Rocket 1", mission.getName());
        assertEquals(Set.of(rocket), mission.getRockets());
    }

    @Test
    void changeStatus() {
        var missionStatus = MissionStatus.IN_PROGRESS;
        var newMission = missionService.createNew(MISSION_ID);
        var mission = missionService.changeStatus(newMission, missionStatus);
        assertEquals(MISSION_ID, mission.getMissionId());
        assertEquals(missionStatus, mission.getMissionStatus());
    }
}