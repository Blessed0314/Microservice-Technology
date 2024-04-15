package com.example.on_class.adapters.driven.jpa.mysql.adapter;

import com.example.on_class.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.example.on_class.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.example.on_class.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.example.on_class.domain.model.Bootcamp;
import com.example.on_class.domain.model.Capacity;
import com.example.on_class.domain.model.Technology;
import com.example.on_class.domain.model.Version;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class VersionAdapterTest {
    @Mock
    private IVersionRepository versionRepository;
    @Mock
    private IVersionEntityMapper versionEntityMapper;

    private VersionAdapter versionAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        versionAdapter = new VersionAdapter(versionRepository, versionEntityMapper);
    }

    @Test
    void saveVersion_SuccessfullySaved() {
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language"));

        List<Capacity> capacities = new ArrayList<>();
        capacities.add(new Capacity(1L, "Proof2", "Fronted", technologies));

        Bootcamp bootcamp = new Bootcamp(1L, "Proof3", "bootcamp description", capacities);
        LocalDate initialDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(30);
        Version version = new Version(1L, 30, initialDate, endDate, bootcamp);

        VersionEntity versionEntity = new VersionEntity();

        when(versionEntityMapper.toEntity(version)).thenReturn(versionEntity);

        versionAdapter.saveVersion(version);

        verify(versionRepository).save(versionEntityMapper.toEntity(version));
    }

    @Test
    void testGetAllVersions_WhenBootcampNotNull() {
        List<VersionEntity> versionEntities = new ArrayList<>();
        versionEntities.add(new VersionEntity());

        Pageable pagination = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "initialDate"));

        Version mockedVersion = mock(Version.class);

        when(versionEntityMapper.toModelList(versionEntities))
                .thenReturn(List.of(mockedVersion));

        Page<VersionEntity> page = new PageImpl<>(versionEntities);
        when(versionRepository.findAllByBootcampName("bootcamp", pagination)).thenReturn(page);

        List<Version> versions = versionAdapter.getAllVersions("bootcamp", 0, 10, 1, true);

        assertNotNull(versions);
        assertEquals(1, versions.size());
    }

    @Test
    void testGetAllVersions_WhenBootcampIsNull() {
        List<VersionEntity> versionEntities = new ArrayList<>();
        versionEntities.add(new VersionEntity());

        Pageable pagination = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "initialDate"));

        Version mockedVersion = mock(Version.class);

        when(versionEntityMapper.toModelList(versionEntities))
                .thenReturn(List.of(mockedVersion));

        Page<VersionEntity> page = new PageImpl<>(versionEntities);
        when(versionRepository.findAll(pagination)).thenReturn(page);

        List<Version> versions = versionAdapter.getAllVersions(null, 0, 10, 1, true);

        assertNotNull(versions);
        assertEquals(1, versions.size());
        verify(versionRepository).findAll(pagination);
    }
}