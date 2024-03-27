package com.example.On_class.adapters.driving.http.controller;

import com.example.On_class.adapters.driving.http.dto.request.AddCapacityRequest;
import com.example.On_class.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.example.On_class.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.example.On_class.domain.api.ICapacityServicePort;
import com.example.On_class.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CapacityRestControllerAdapterTest {

    @Mock
    private ICapacityServicePort capacityServicePort;
    @Mock
    private ICapacityRequestMapper capacityRequestMapper;
    @Mock
    private ICapacityResponseMapper capacityResponseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCapacity() {
        CapacityRestControllerAdapter controller = new CapacityRestControllerAdapter(
                capacityServicePort, capacityRequestMapper, capacityResponseMapper);
        Technology technology1 = new Technology(1L, "Java", "Programming Language" );
        List<Technology> technologies = new ArrayList<>();
        technologies.add(technology1);

        AddCapacityRequest request = new AddCapacityRequest("Proof2", "Fronted",  technologies);;
        when(capacityRequestMapper.addRequestToCapacity(request)).thenReturn(any());

        ResponseEntity<Void> response = controller.addCapacity(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(capacityServicePort,times(1)).saveCapacity(any());

    }
}