package com.kkazmierczak.assessment.mission.summary;

import com.kkazmierczak.assessment.mission.Mission;
import com.kkazmierczak.assessment.mission.MissionRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
class MissionSummaryServiceImpl implements MissionSummaryService {
    private final MissionRepository missionRepository;

    @Override
    public List<Mission> getMissionsByRocketsAssignedDescending() {
        var allMissions = missionRepository.findAll();
        return allMissions.stream()
                .sorted()
                .toList();
    }
}
