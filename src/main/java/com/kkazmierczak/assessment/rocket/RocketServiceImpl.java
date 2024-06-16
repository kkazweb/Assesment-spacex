package com.kkazmierczak.assessment.rocket;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class RocketServiceImpl implements RocketService {
    private final RocketRepository rocketRepository;

    @Override
    public Rocket createNew(long rocketId, String name) {
        if(rocketRepository.isExistingId(rocketId)){
            throw new IllegalArgumentException(String.format("Rocket with id %d already exists.", rocketId));
        }
        return new Rocket(rocketId, name);
    }

    @Override
    public Rocket changeStatus(Rocket rocket, RocketStatus rocketStatus) {
        return new Rocket(rocket.getRocketId(), rocket.getName(), rocketStatus, rocket.isAssigned());
    }
}
