package com.example.On_class.configuration;

import com.example.On_class.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.example.On_class.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.example.On_class.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.example.On_class.domain.api.ITechnologyServicePort;
import com.example.On_class.domain.api.usecase.TechnologyUseCase;
import com.example.On_class.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;
    @Bean
    public ITechnologyPersistencePort technologyPersistencePort(){
        return new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }
    @Bean
    public ITechnologyServicePort technologyServicePort(){
        return new TechnologyUseCase(technologyPersistencePort());
    }
}
