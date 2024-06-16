package com.kkazmierczak.assessment.mission;

import com.kkazmierczak.assessment.rocket.Rocket;

interface MissionService {
    Mission add(long missionId);
    Mission assignRocket(Mission mission, Rocket rocket);
    Mission changeStatus(Mission mission, MissionStatus missionStatus);
}
