package com.example.on_class.configuration;

import com.example.on_class.adapters.driven.jpa.mysql.adapter.BootcampAdapter;
import com.example.on_class.adapters.driven.jpa.mysql.adapter.CapacityAdapter;
import com.example.on_class.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.example.on_class.adapters.driven.jpa.mysql.adapter.VersionAdapter;
import com.example.on_class.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.example.on_class.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.example.on_class.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.example.on_class.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.example.on_class.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.example.on_class.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.example.on_class.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.example.on_class.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.example.on_class.domain.api.IBootcampServicePort;
import com.example.on_class.domain.api.ICapacityServicePort;
import com.example.on_class.domain.api.ITechnologyServicePort;
import com.example.on_class.domain.api.IVersionServicePort;
import com.example.on_class.domain.api.usecase.BootcampUseCase;
import com.example.on_class.domain.api.usecase.CapacityUseCase;
import com.example.on_class.domain.api.usecase.TechnologyUseCase;
import com.example.on_class.domain.api.usecase.VersionUseCase;
import com.example.on_class.domain.spi.IBootcampPersistencePort;
import com.example.on_class.domain.spi.ICapacityPersistencePort;
import com.example.on_class.domain.spi.ITechnologyPersistencePort;
import com.example.on_class.domain.spi.IVersionPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    private final ICapacityRepository capacityRepository;
    private final ICapacityEntityMapper capacityEntityMapper;

    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;

    private final IVersionRepository versionRepository;
    private final IVersionEntityMapper versionEntityMapper;
    @Bean
    public ITechnologyPersistencePort technologyPersistencePort(){
        return new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }
    @Bean
    public ITechnologyServicePort technologyServicePort(){
        return new TechnologyUseCase(technologyPersistencePort());
    }

    @Bean
    public ICapacityPersistencePort capacityPersistencePort(){
        return new CapacityAdapter(capacityRepository, capacityEntityMapper);
    }
    @Bean
    public ICapacityServicePort capacityServicePort(){
        return new CapacityUseCase(capacityPersistencePort());
    }

    @Bean
    public IBootcampPersistencePort bootcampPersistencePort(){
        return new BootcampAdapter(bootcampRepository, bootcampEntityMapper);
    }
    @Bean
    public IBootcampServicePort bootcampServicePort(){
        return new BootcampUseCase(bootcampPersistencePort());
    }

    @Bean
    public IVersionPersistencePort versionPersistencePort(){
        return new VersionAdapter(versionRepository, versionEntityMapper);
    }
    @Bean
    public IVersionServicePort versionServicePort(){
        return new VersionUseCase(versionPersistencePort());
    }
}
