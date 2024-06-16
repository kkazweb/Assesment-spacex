package com.kkazmierczak.assessment.rocket;

interface RocketService {
    Rocket createNew(long rocketId, String name);
    Rocket changeStatus(Rocket rocket, RocketStatus rocketStatus);
}
