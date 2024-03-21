package com.example.On_class.adapters.driving.http.controller;

import com.example.On_class.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.example.On_class.adapters.driving.http.dto.response.TechnologyResponse;
import com.example.On_class.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.example.On_class.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.example.On_class.domain.api.ITechnologyServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TechnologyRestControllerAdapterTest {

    @Mock
    private ITechnologyServicePort technologyServicePort;

    @Mock
    private ITechnologyRequestMapper technologyRequestMapper;

    @Mock
    private ITechnologyResponseMapper technologyResponseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddTechnology() {
        TechnologyRestControllerAdapter controller = new TechnologyRestControllerAdapter(
                technologyServicePort, technologyRequestMapper, technologyResponseMapper);

        AddTechnologyRequest request = new AddTechnologyRequest("JavaScript","Language programming");
        when(technologyRequestMapper.addRequestToTechnology(request)).thenReturn(any());

        ResponseEntity<Void> response = controller.addTechnology(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(technologyServicePort, times(1)).saveTechnology(any());
    }

    @Test
    void  testGetAllTechnologies (){
        TechnologyRestControllerAdapter adapter = new TechnologyRestControllerAdapter(
                technologyServicePort, technologyRequestMapper, technologyResponseMapper
        );

        int page = 1;
        int size = 5;
        boolean ascendingFlag = true;

        List<TechnologyResponse> mockResponseList = Collections.singletonList(new TechnologyResponse(
                2L,"Javascript","Programming language"));

        when(technologyServicePort.getAllTechnologies(page, size, ascendingFlag)).thenReturn(Collections.emptyList());
        when(technologyResponseMapper.toTechnologyResponseList(Collections.emptyList())).thenReturn(mockResponseList);

        ResponseEntity<List<TechnologyResponse>> responseEntity = adapter.getTechnologyList(page, size, ascendingFlag);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponseList, responseEntity.getBody());
        verify(technologyServicePort, times(1)).getAllTechnologies(page, size, ascendingFlag);
        verify(technologyResponseMapper, times(1)).toTechnologyResponseList(Collections.emptyList());
    }
}