package com.kkazmierczak.assessment.mission;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MissionViewerImpl implements MissionViewer {
    private final MissionRepository missionRepository;

    @Override
    public List<Mission> getMissionsByRocketsAssignedDescending() {
        var allMissions = missionRepository.findAll();
        return allMissions.stream()
                .sorted()
                .toList();
    }
}
