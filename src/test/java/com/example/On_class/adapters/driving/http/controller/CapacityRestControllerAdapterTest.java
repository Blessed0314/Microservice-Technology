package com.example.On_class.adapters.driving.http.controller;

import com.example.On_class.adapters.driving.http.dto.request.AddCapacityRequest;
import com.example.On_class.adapters.driving.http.dto.response.CapacityResponse;
import com.example.On_class.adapters.driving.http.dto.response.TechnologyToCapacityResponse;
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

import java.util.Collections;
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

    @Test
    void testGetAllCapacities(){
        CapacityRestControllerAdapter adapter = new CapacityRestControllerAdapter(
                capacityServicePort, capacityRequestMapper, capacityResponseMapper
        );

        int page = 1;
        int size = 5;
        boolean orderFlag = true;
        boolean ascendingFlag = true;

        List<TechnologyToCapacityResponse> technologies = new ArrayList<>();
        TechnologyToCapacityResponse technology = new TechnologyToCapacityResponse(1L,"ProofTechnology");
        technologies.add(technology);

        List<CapacityResponse> mockResponseList = Collections.singletonList(new CapacityResponse(
                2L, "Proof1", "description proof", technologies));

        when(capacityServicePort.getAllCapacities(page, size, orderFlag, ascendingFlag)).thenReturn(Collections.emptyList());
        when(capacityResponseMapper.toCapacityResponseList(Collections.emptyList())).thenReturn(mockResponseList);

        ResponseEntity<List<CapacityResponse>> responseEntity = adapter.getCapacityList(page, size, orderFlag, ascendingFlag);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponseList, responseEntity.getBody());
        verify(capacityServicePort, times(1)).getAllCapacities(page, size, orderFlag, ascendingFlag);
        verify(capacityResponseMapper, times(1)).toCapacityResponseList(Collections.emptyList());
    }
}