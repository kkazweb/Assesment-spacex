package com.kkazmierczak.assessment.mission.assignment;

import com.kkazmierczak.assessment.mission.Mission;
import com.kkazmierczak.assessment.rocket.Rocket;

interface MissionAssignmentService {
    Mission assignRocket(Mission mission, Rocket rocket);
}
