package com.kkazmierczak.assessment.mission;

import com.kkazmierczak.assessment.rocket.Rocket;
import com.kkazmierczak.assessment.rocket.RocketStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class MissionServiceImplTest {
    @Mock
    private MissionRepository missionRepository;
    private MissionService missionService;
    private final long MISSION_ID = 90532095L;
    private final String MISSION_NAME = "MISSION 342";

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        missionService = new MissionServiceImpl(missionRepository);
        when(missionRepository.findAll()).thenCallRealMethod();
        when(missionRepository.isExistingId(anyLong())).thenCallRealMethod();
    }

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
        assertTrue(assignedRocketMission.getRockets().stream().findFirst().get().isAssigned());
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

    @Test
    void assignAssignedRocketThrowException(){
        var assignedRocket = new Rocket(33L, "Rocket 1", RocketStatus.ON_GROUND, true);
        var newMission = missionService.createNew(MISSION_ID, MISSION_NAME);
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> missionService.assignRocket(newMission, assignedRocket));
        assertEquals("Error while trying to assign rocket id 33 to mission id 90532095.", illegalArgumentException.getMessage());
    }

    @Test
    void assignRocketMissionEndedThrowException(){
        var assignedRocket = new Rocket(33L, "Rocket 1", RocketStatus.ON_GROUND, false);
        var newMission = new Mission(MISSION_ID, "Ended mission name", MissionStatus.ENDED, Set.of());
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> missionService.assignRocket(newMission, assignedRocket));
        assertEquals("Error while trying to assign rocket id 33 to mission id 90532095.", illegalArgumentException.getMessage());
    }

    @Test
    void createMissionMissionIdExists() {
        var existingMissionId = 95832095L;
        var missions = missionRepository.findAll();
        assertTrue(missions.stream().anyMatch(mission -> mission.getMissionId() == existingMissionId));
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> missionService.createNew(existingMissionId, "Mission with this ID already exists."));
        assertEquals("Mission with id 95832095 already exists.", illegalArgumentException.getMessage());
    }
}