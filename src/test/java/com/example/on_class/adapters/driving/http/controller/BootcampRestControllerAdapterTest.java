package com.example.on_class.adapters.driving.http.controller;

import com.example.on_class.adapters.driving.http.dto.request.AddBootcampRequest;
import com.example.on_class.adapters.driving.http.dto.response.BootcampResponse;
import com.example.on_class.adapters.driving.http.dto.response.CapacityResponse;
import com.example.on_class.adapters.driving.http.dto.response.CapacityToBootcampResponse;
import com.example.on_class.adapters.driving.http.dto.response.TechnologyToCapacityResponse;
import com.example.on_class.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.example.on_class.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.example.on_class.domain.api.IBootcampServicePort;
import com.example.on_class.domain.model.Capacity;
import com.example.on_class.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BootcampRestControllerAdapterTest {
    @Mock
    private IBootcampServicePort bootcampServicePort;
    @Mock
    private IBootcampRequestMapper bootcampRequestMapper;
    @Mock
    private IBootcampResponseMapper bootcampResponseMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBootcamp() {
        BootcampRestControllerAdapter controller = new BootcampRestControllerAdapter(
               bootcampServicePort, bootcampRequestMapper, bootcampResponseMapper);

        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language" ));

        List<Capacity> capacities = new ArrayList<>();
        capacities.add(new Capacity(1L, "Proof2", "Fronted", technologies));

        AddBootcampRequest request = new AddBootcampRequest("Proof3", "Bootcamp description", capacities);
        when(bootcampRequestMapper.addRequestToBootcamp(request)).thenReturn(any());

        ResponseEntity<Void> response = controller.addBootcamp(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(bootcampServicePort, times(1)).saveBootcamp(any());
    }

    @Test
    void testGetAllBootcamps() {
        BootcampRestControllerAdapter adapter = new BootcampRestControllerAdapter(
                bootcampServicePort, bootcampRequestMapper, bootcampResponseMapper
        );

        int page = 1;
        int size = 5;
        boolean orderFlag = true;
        boolean ascendingFlag = true;

        List<BootcampResponse> mockResponseList = getBootcampResponses();

        when(bootcampServicePort.getAllBootcamps(page, size, orderFlag, ascendingFlag)).thenReturn(Collections.emptyList());
        when(bootcampResponseMapper.toBootcampResponseList(Collections.emptyList())).thenReturn(mockResponseList);

        ResponseEntity<List<BootcampResponse>> responseEntity = adapter.getBootcampList(page, size, orderFlag, ascendingFlag);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponseList, responseEntity.getBody());
        verify(bootcampServicePort, times(1)).getAllBootcamps(page, size, orderFlag, ascendingFlag);
        verify(bootcampResponseMapper, times(1)).toBootcampResponseList(Collections.emptyList());
    }

    private static List<BootcampResponse> getBootcampResponses() {
        List<TechnologyToCapacityResponse> technologies = new ArrayList<>();
        TechnologyToCapacityResponse technology = new TechnologyToCapacityResponse(1L,"ProofTechnology");
        technologies.add(technology);

        List<CapacityToBootcampResponse> capacities = new ArrayList<>();
        CapacityToBootcampResponse capacity = new CapacityToBootcampResponse(2L, "Proof3", technologies);
        capacities.add(capacity);

        return Collections.singletonList(new BootcampResponse(
                2L,"Proof4", "description proof", capacities));
    }
}