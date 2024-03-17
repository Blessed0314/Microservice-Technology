package com.example.On_class.adapters.driven.jpa.mysql.adapter;

import com.example.On_class.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.example.On_class.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.example.On_class.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.example.On_class.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TechnologyAdapterTest {

    @Mock
    private ITechnologyRepository technologyRepository;
    @Mock
    private ITechnologyEntityMapper technologyEntityMapper;
    private TechnologyAdapter technologyAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        technologyAdapter = new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }

    @Test
    void saveTechnology_SuccessfullySaved() {

        Technology technology = new Technology(2L,"JavaScript", "Programming Language");
        TechnologyEntity technologyEntity = new TechnologyEntity();

        when(technologyRepository.findByName(technology.getName())).thenReturn(Optional.empty());
        when(technologyEntityMapper.toEntity(technology)).thenReturn(technologyEntity);

        technologyAdapter.saveTechnology(technology);

        verify(technologyRepository).save(technologyEntity);
    }

}