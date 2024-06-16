package com.kkazmierczak.assessment.mission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MissionViewerImplTest {

    @Mock
    private MissionRepository missionRepository;
    private MissionViewer missionViewer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        missionViewer = new MissionViewerImpl(missionRepository);
        when(missionRepository.findAll()).thenCallRealMethod();
    }

    @Test
    void getMissionsByRocketsAssignedDescending() {
        var allMissions = missionViewer.getMissionsByRocketsAssignedDescending();
        assertEquals(6, allMissions.size());
        assertEquals("Transit", allMissions.get(0).getName());
        assertEquals("Luna1", allMissions.get(1).getName());
        assertEquals("Vertical Landing", allMissions.get(2).getName());
        assertEquals("Mars", allMissions.get(3).getName());
        assertEquals("Luna2", allMissions.get(4).getName());
        assertEquals("Double Landing", allMissions.get(5).getName());

    }
}