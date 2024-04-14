package com.example.on_class.adapters.driving.http.controller;

import com.example.on_class.adapters.driving.http.dto.request.AddVersionRequest;
import com.example.on_class.adapters.driving.http.mapper.IVersionRequestMapper;
import com.example.on_class.adapters.driving.http.mapper.IVersionResponseMapper;
import com.example.on_class.domain.api.IVersionServicePort;
import com.example.on_class.domain.model.Bootcamp;
import com.example.on_class.domain.model.Capacity;
import com.example.on_class.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VersionRestControllerAdapterTest {
    @Mock
    private IVersionServicePort versionServicePort;
    @Mock
    private IVersionRequestMapper versionRequestMapper;
    @Mock
    private IVersionResponseMapper versionResponseMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddValidVersion() {
        VersionRestControllerAdapter controller = new VersionRestControllerAdapter(
                versionServicePort, versionRequestMapper, versionResponseMapper);

        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language" ));

        List<Capacity> capacities = new ArrayList<>();
        capacities.add(new Capacity(1L, "Proof2", "Fronted", technologies));

        int quota = 10;
        LocalDate initialDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(30);
        Bootcamp bootcamp = new Bootcamp(1L, "Proof3", "Bootcamp description", capacities);

        AddVersionRequest request = new AddVersionRequest(quota, initialDate, endDate, bootcamp);
        when(versionRequestMapper.addRequestVersion(request)).thenReturn(any());

        ResponseEntity<Void> response = controller.addVersion(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(versionServicePort, times(1)).saveVersion(any());
    }
}