package com.example.on_class.adapters.driven.jpa.mysql.adapter;

import com.example.on_class.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.example.on_class.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.on_class.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.example.on_class.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.example.on_class.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.example.on_class.domain.model.Technology;
import com.example.on_class.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;


    @Override
    public void saveTechnology(Technology technology) {
        Optional<TechnologyEntity> existingTechnology = technologyRepository.findByName(technology.getName());
        if (existingTechnology.isPresent()){
            throw new TechnologyAlreadyExistsException();
        }
        technologyRepository.save(technologyEntityMapper.toEntity(technology));
    }

    @Override
    public List<Technology> getAllTechnologies(Integer page, Integer size, boolean orderFlag) {

        Sort sort = orderFlag ? Sort.by(Sort.Direction.ASC, "name") : Sort.by(Sort.Direction.DESC, "name");
        Pageable pagination = PageRequest.of(page, size, sort);
        List<TechnologyEntity> technologies = technologyRepository.findAll(pagination).getContent();

        if (technologies.isEmpty()) {
            throw new NoDataFoundException();
        }
        return technologyEntityMapper.toModelList(technologies);
    }
}
