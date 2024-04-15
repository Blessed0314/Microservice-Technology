package com.example.on_class.adapters.driving.http.controller;

import com.example.on_class.adapters.driving.http.dto.request.AddBootcampRequest;
import com.example.on_class.adapters.driving.http.dto.request.AddVersionRequest;
import com.example.on_class.adapters.driving.http.dto.response.*;
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
import java.util.Arrays;
import java.util.Collections;
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

        Bootcamp bootcamp = new Bootcamp(1L,"Proof3", "Bootcamp description", capacities);

        LocalDate initialDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(30);

        AddVersionRequest request = new AddVersionRequest(10, initialDate, endDate, bootcamp);
        when(versionRequestMapper.addRequestVersion(request)).thenReturn(any());

        ResponseEntity<Void> response = controller.addVersion(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(versionServicePort, times(1)).saveVersion(any());
    }

    @Test
    void testGetVersionListWithoutBootcampParameter() {
        VersionRestControllerAdapter adapter = new VersionRestControllerAdapter(
                versionServicePort, versionRequestMapper, versionResponseMapper);

        int page = 1;
        int size = 5;
        int orderFlag = 1;
        boolean ascendingFlag = true;

        List<VersionResponse> mockResponseList = getVersionResponses(false);

        when(versionServicePort.getAllVersions(null, page, size, orderFlag, ascendingFlag)).thenReturn(Collections.emptyList());
        when(versionResponseMapper.toVersionResponseList(Collections.emptyList())).thenReturn(mockResponseList);

        ResponseEntity<List<VersionResponse>> responseEntity = adapter.getVersionList(null, page, size, orderFlag, ascendingFlag);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponseList, responseEntity.getBody());
        verify(versionServicePort, times(1)).getAllVersions(null, page, size, orderFlag, ascendingFlag);
        verify(versionResponseMapper, times(1)).toVersionResponseList(Collections.emptyList());
    }

    @Test
    void testGetVersionListWithBootcampParameter() {
        VersionRestControllerAdapter adapter = new VersionRestControllerAdapter(
                versionServicePort, versionRequestMapper, versionResponseMapper);

        int page = 1;
        int size = 5;
        int orderFlag = 1;
        boolean ascendingFlag = true;

        List<VersionResponse> mockResponseList = getVersionResponses(true);

        when(versionServicePort.getAllVersions("Proof3", page, size, orderFlag, ascendingFlag)).thenReturn(Collections.emptyList());
        when(versionResponseMapper.toVersionResponseList(Collections.emptyList())).thenReturn(mockResponseList);

        ResponseEntity<List<VersionResponse>> responseEntity = adapter.getVersionList("Proof3", page, size, orderFlag, ascendingFlag);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponseList, responseEntity.getBody());
        verify(versionServicePort, times(1)).getAllVersions("Proof3", page, size, orderFlag, ascendingFlag);
        verify(versionResponseMapper, times(1)).toVersionResponseList(Collections.emptyList());
    }

    private static List<VersionResponse> getVersionResponses(boolean existParameter) {
        List<TechnologyToCapacityResponse> technologies = new ArrayList<>();
        technologies.add(new TechnologyToCapacityResponse(1L, "Java"));

        List<CapacityToBootcampResponse> capacities = new ArrayList<>();
        capacities.add(new CapacityToBootcampResponse(1L, "Proof2", technologies));

        BootcampResponse bootcamp1 = new BootcampResponse(1L, "Proof3", "bootcamp description", capacities);
        BootcampResponse bootcamp2 = new BootcampResponse(2L, "Proof4", "bootcamp description", capacities);

        LocalDate initialDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(30);

        return existParameter
                ? new ArrayList<>(Arrays.asList(
                        new VersionResponse(1L, 30, initialDate, endDate, bootcamp1),
                        new VersionResponse(2L, 30, initialDate, endDate, bootcamp2)))
                : new ArrayList<>(Arrays.asList(
                        new VersionResponse(1L, 30, initialDate, endDate, bootcamp1),
                        new VersionResponse(2L, 30, initialDate, endDate, bootcamp1)));
    }
}