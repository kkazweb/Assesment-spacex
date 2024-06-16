package com.kkazmierczak.assessment.mission;

import java.util.Set;

interface MissionViewer {
    Set<Mission> getMissionsByRocketsAssigned(long rockets);
}
