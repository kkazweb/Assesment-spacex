package com.kkazmierczak.assessment.rocket;

interface RocketService {
    Rocket add();
    Rocket changeStatus(RocketStatus rocketStatus);
}
