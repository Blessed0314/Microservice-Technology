package com.example.On_class.adapters.driven.jpa.mysql.adapter;

import com.example.On_class.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.example.On_class.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.On_class.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.example.On_class.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.example.On_class.domain.model.Capacity;
import com.example.On_class.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

@RequiredArgsConstructor
public class CapacityAdapter implements ICapacityPersistencePort {
    private final ICapacityRepository capacityRepository;
    private final ICapacityEntityMapper capacityEntityMapper;


    @Override
    public void saveCapacity(Capacity capacity) {
        capacityRepository.save(capacityEntityMapper.toEntity(capacity));
    }

    @Override
    public List<Capacity> getAllCapacities(Integer page, Integer size, boolean orderFlag, boolean ascendingFlag) {
        List<CapacityEntity> capacities;
        Sort sort;
        Pageable pagination;
        if (orderFlag) {
            sort = ascendingFlag
                ? Sort.by(Sort.Direction.ASC, "name")
                : Sort.by(Sort.Direction.DESC, "name");
            pagination = PageRequest.of(page, size, sort);
            capacities = capacityRepository.findAll(pagination).getContent();
        } else {
            pagination = PageRequest.of(page, size);
            capacities = capacityRepository.findAllOrderByTechnologies(pagination, ascendingFlag).getContent();
        }

        if (capacities.isEmpty()) {
            throw new NoDataFoundException();
        }
        return capacityEntityMapper.toModelList(capacities);
    }
}
