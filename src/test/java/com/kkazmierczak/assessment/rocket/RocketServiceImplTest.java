package com.kkazmierczak.assessment.rocket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class RocketServiceImplTest {
    @Mock
    private RocketRepository rocketRepository;
    private RocketService rocketService;
    private final long ROCKET_ID = 90532095L;
    private final String ROCKET_NAME = "ROCKET 001";

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        rocketService = new RocketServiceImpl(rocketRepository);
        when(rocketRepository.findAll()).thenCallRealMethod();
        when(rocketRepository.isExistingId(anyLong())).thenCallRealMethod();
    }

    @Test
    void createNew() {
        var newRocket = rocketService.createNew(ROCKET_ID, ROCKET_NAME);
        assertEquals(ROCKET_ID, newRocket.getRocketId());
        assertEquals(ROCKET_NAME, newRocket.getName());
        assertEquals(RocketStatus.ON_GROUND, newRocket.getRocketStatus());
    }

    @Test
    void changeStatus() {
        var newRocket = rocketService.createNew(ROCKET_ID, ROCKET_NAME);
        var changedStatusRocket = rocketService.changeStatus(newRocket, RocketStatus.IN_REPAIR);
        assertEquals(ROCKET_ID, changedStatusRocket.getRocketId());
        assertEquals(ROCKET_NAME, changedStatusRocket.getName());
        assertEquals(RocketStatus.IN_REPAIR, changedStatusRocket.getRocketStatus());
    }

    @Test
    void createRocketRocketIdExists() {
        var existingRocketId = 6238683029L;
        var rockets = rocketRepository.findAll();
        assertTrue(rockets.stream().anyMatch(rocket -> rocket.getRocketId() == existingRocketId));
        var illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> rocketService.createNew(existingRocketId, "Rocket with this ID already exists."));
        assertEquals("Rocket with id 6238683029 already exists.", illegalArgumentException.getMessage());
    }
}