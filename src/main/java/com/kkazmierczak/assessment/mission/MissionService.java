package com.kkazmierczak.assessment.mission;

interface MissionService {
    Mission createNew(long missionId, String name);
    Mission changeStatus(Mission mission, MissionStatus missionStatus);
}
