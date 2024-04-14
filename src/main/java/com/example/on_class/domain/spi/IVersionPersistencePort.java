package com.example.on_class.domain.spi;

import com.example.on_class.domain.model.Version;

public interface IVersionPersistencePort {
    void saveVersion(Version version);
}
