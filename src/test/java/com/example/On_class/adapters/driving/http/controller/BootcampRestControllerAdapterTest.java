package com.example.On_class.adapters.driving.http.controller;

import com.example.On_class.adapters.driving.http.dto.request.AddBootcampRequest;
import com.example.On_class.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.example.On_class.domain.api.IBootcampServicePort;
import com.example.On_class.domain.model.Bootcamp;
import com.example.On_class.domain.model.Capacity;
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

class BootcampRestControllerAdapterTest {
    @Mock
    private IBootcampServicePort bootcampServicePort;
    @Mock
    private IBootcampRequestMapper bootcampRequestMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBootcamp() {
        BootcampRestControllerAdapter controller = new BootcampRestControllerAdapter(
               bootcampServicePort, bootcampRequestMapper);

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
}