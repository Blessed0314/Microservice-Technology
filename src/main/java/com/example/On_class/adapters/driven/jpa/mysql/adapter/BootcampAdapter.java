package com.example.On_class.adapters.driven.jpa.mysql.adapter;

import com.example.On_class.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.example.On_class.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.example.On_class.domain.model.Bootcamp;
import com.example.On_class.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BootcampAdapter implements IBootcampPersistencePort {
    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;
    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        bootcampRepository.save(bootcampEntityMapper.toEntity(bootcamp));
    }
}
