package com.kkazmierczak.assessment.mission;

import java.util.List;

interface MissionRepository {
    List<Mission> findAll();
}
