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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}