package com.example.on_class.adapters.driven.jpa.mysql.adapter;

import com.example.on_class.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.example.on_class.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.on_class.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.example.on_class.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.example.on_class.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TechnologyAdapterTest {
    @Mock
    private ITechnologyRepository technologyRepository;
    @Mock
    private ITechnologyEntityMapper technologyEntityMapper;
    private TechnologyAdapter technologyAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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

    @Test
    void getAllTechnologies_Error_NoDataFound() {
        int page = 0;
        int size = 20;
        boolean ascendingFlag = true;

        PageRequest pageable = PageRequest.of(page, size, Sort.by(ascendingFlag ? Sort.Direction.ASC : Sort.Direction.DESC, "name"));

        when(technologyRepository.findAll(pageable)).thenReturn(new PageImpl<>(Collections.emptyList()));

        assertThrows(NoDataFoundException.class, () -> technologyAdapter.getAllTechnologies(page, size, ascendingFlag));

        verify(technologyRepository).findAll(pageable);
        verifyNoInteractions(technologyEntityMapper);
    }
}