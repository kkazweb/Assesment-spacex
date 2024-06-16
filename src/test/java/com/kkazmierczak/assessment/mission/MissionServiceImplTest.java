package com.kkazmierczak.assessment.mission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void changeStatus() {
        var missionStatus = MissionStatus.IN_PROGRESS;
        var newMission = missionService.createNew(MISSION_ID, MISSION_NAME);
        var changedStatusMission = missionService.changeStatus(newMission, missionStatus);
        assertEquals(MISSION_ID, changedStatusMission.getMissionId());
        assertEquals(MISSION_NAME, changedStatusMission.getName());
        assertEquals(missionStatus, changedStatusMission.getMissionStatus());
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