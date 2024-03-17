package com.example.On_class.adapters.driving.http.controller;

import com.example.On_class.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.example.On_class.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.example.On_class.domain.api.ITechnologyServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TechnologyRestControllerAdapterTest {

    @Mock
    private ITechnologyServicePort technologyServicePort;

    @Mock
    private ITechnologyRequestMapper technologyRequestMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddTechnology() {
        TechnologyRestControllerAdapter controller = new TechnologyRestControllerAdapter(
                technologyServicePort, technologyRequestMapper);

        AddTechnologyRequest request = new AddTechnologyRequest("JavaScript","Language programming");
        when(technologyRequestMapper.addRequestToTechnology(request)).thenReturn(any());

        ResponseEntity<Void> response = controller.addTechnology(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(technologyServicePort, times(1)).saveTechnology(any());
    }
}