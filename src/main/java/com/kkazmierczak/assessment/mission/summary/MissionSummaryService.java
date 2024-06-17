package com.kkazmierczak.assessment.mission.summary;

import com.kkazmierczak.assessment.mission.Mission;

import java.util.List;

interface MissionSummaryService {
    List<Mission> getMissionsByRocketsDescending();
}
