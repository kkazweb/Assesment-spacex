package com.kkazmierczak.assessment.mission;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
class MissionServiceImpl implements MissionService {
    private final MissionRepository missionRepository;

    @Override
    public Mission createNew(long missionId, String name) {
        if(missionRepository.isExistingId(missionId)){
            throw new IllegalArgumentException(String.format("Mission with id %d already exists.", missionId));
        }
        return new Mission(missionId, name, Set.of());
    }

    @Override
    public Mission changeStatus(Mission mission, MissionStatus missionStatus) {
        return new Mission(mission.getMissionId(), mission.getName(), missionStatus, mission.getRockets());
    }
}
