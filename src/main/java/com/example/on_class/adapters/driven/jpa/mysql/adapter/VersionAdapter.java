package com.example.on_class.adapters.driven.jpa.mysql.adapter;

import com.example.on_class.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.example.on_class.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.example.on_class.domain.model.Version;
import com.example.on_class.domain.spi.IVersionPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VersionAdapter implements IVersionPersistencePort {
    private final IVersionRepository versionRepository;
    private final IVersionEntityMapper versionEntityMapper;
    @Override
    public void saveVersion(Version version) {
        versionRepository.save(versionEntityMapper.toEntity(version));
    }
}
