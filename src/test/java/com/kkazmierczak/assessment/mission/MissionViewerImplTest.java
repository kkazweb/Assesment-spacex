package com.kkazmierczak.assessment.mission;

import com.kkazmierczak.assessment.rocket.Rocket;
import com.kkazmierczak.assessment.rocket.RocketStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MissionViewerImplTest {
    @Mock
    private MissionRepository missionRepository;
    private MissionViewer missionViewer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        missionViewer = new MissionViewerImpl(missionRepository);
        when(missionRepository.findAll())
                .thenReturn(getInitialData());
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

    private static List<Mission> getInitialData() {
        return List.of(
                new Mission(95832095L,
                        "Mars",
                        MissionStatus.SCHEDULED,
                        Set.of()),
                new Mission(960238460982L,
                        "Luna1",
                        MissionStatus.PENDING,
                        Set.of(new Rocket(6238683029L,
                                        "Dragon 1",
                                        RocketStatus.ON_GROUND,
                                        true),
                                new Rocket(53129050212L,
                                        "Dragon 2",
                                        RocketStatus.ON_GROUND,
                                        true))
                ),
                new Mission(629034690423L,
                        "Double Landing",
                        MissionStatus.ENDED,
                        Set.of()),
                new Mission(69023682351L,
                        "Transit",
                        MissionStatus.IN_PROGRESS,
                        Set.of(new Rocket(23859523L,
                                        "Red Dragon",
                                        RocketStatus.ON_GROUND,
                                        true),
                                new Rocket(590320532L,
                                        "Dragon XL",
                                        RocketStatus.IN_SPACE,
                                        true),
                                new Rocket(6041234128542L,
                                        "Falcon Heavy",
                                        RocketStatus.IN_SPACE,
                                        true))
                ),
                new Mission(6329602386124L,
                        "Luna2",
                        MissionStatus.SCHEDULED,
                        Set.of()),
                new Mission(1258912358923L,
                        "Vertical Landing",
                        MissionStatus.ENDED,
                        Set.of())
        );
    }
}