package com.kkazmierczak.assessment.rocket;

import com.kkazmierczak.assessment.mission.Mission;

interface RocketService {
    Rocket add();
    Rocket changeStatus(RocketStatus rocketStatus);
}
