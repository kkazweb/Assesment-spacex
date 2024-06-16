package com.kkazmierczak.assessment.mission.assignment;

import com.kkazmierczak.assessment.mission.Mission;
import com.kkazmierczak.assessment.mission.MissionStatus;
import com.kkazmierczak.assessment.rocket.Rocket;
import com.kkazmierczak.assessment.rocket.RocketStatus;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MissionAssignmentServiceImplTest {
    private final long MISSION_ID = 90532095L;
    private final String MISSION_NAME = "MISSION 342";
    private final MissionAssignmentServiceImpl missionAssignmentService1 = new MissionAssignmentServiceImpl();

    @Test
    void assignRocket() {
        var rocket = new Rocket(9583295L, "Rocket 1");
        var newMission = new Mission(MISSION_ID, MISSION_NAME, Set.of());
        var assignedRocketMission = missionAssignmentService1.assignRocket(newMission, rocket);
        assertEquals(MISSION_ID, assignedRocketMission.getMissionId());
        assertEquals(MISSION_NAME, assignedRocketMission.getName());
        assertEquals(Set.of(rocket), assignedRocketMission.getRockets());
        assertTrue(assignedRocketMission.getRockets().stream().findFirst().get().isAssigned());
    }

    @Test
    void assignAssignedRocketThrowException(){
        var assignedRocket = new Rocket(33L, "Rocket 1", RocketStatus.ON_GROUND, true);
        var newMission = new Mission(MISSION_ID, MISSION_NAME, Set.of());
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> missionAssignmentService1.assignRocket(newMission, assignedRocket));
        assertEquals("Error while trying to assign rocket id 33 to mission id 90532095.", illegalArgumentException.getMessage());
    }

    @Test
    void assignRocketMissionEndedThrowException(){
        var assignedRocket = new Rocket(33L, "Rocket 1", RocketStatus.ON_GROUND, false);
        var newMission = new Mission(MISSION_ID, "Ended mission name", MissionStatus.ENDED, Set.of());
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> missionAssignmentService1.assignRocket(newMission, assignedRocket));
        assertEquals("Error while trying to assign rocket id 33 to mission id 90532095.", illegalArgumentException.getMessage());
    }
}