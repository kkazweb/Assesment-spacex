package com.kkazmierczak.assessment.mission;

import java.util.List;

interface MissionViewer {
    List<Mission> getMissionsByRocketsAssignedDescending();
}
