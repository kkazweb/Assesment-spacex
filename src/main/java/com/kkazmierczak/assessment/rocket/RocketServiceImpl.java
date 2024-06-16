package com.kkazmierczak.assessment.rocket;

class RocketServiceImpl implements RocketService {
    @Override
    public Rocket createNew(long rocketId, String name) {
        return new Rocket(rocketId, name);
    }

    @Override
    public Rocket changeStatus(Rocket rocket, RocketStatus rocketStatus) {
        return new Rocket(rocket.getRocketId(), rocket.getName(), rocketStatus, rocket.isAssigned());
    }
}
