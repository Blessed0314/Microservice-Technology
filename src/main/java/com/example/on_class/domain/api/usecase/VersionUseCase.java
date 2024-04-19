package com.example.on_class.domain.api.usecase;

import com.example.on_class.domain.api.IVersionServicePort;
import com.example.on_class.domain.exception.IncorrectEndDateException;
import com.example.on_class.domain.exception.IncorrectInitialDateException;
import com.example.on_class.domain.model.Version;
import com.example.on_class.domain.spi.IVersionPersistencePort;

import java.time.LocalDate;

public class VersionUseCase implements IVersionServicePort {
    private IVersionPersistencePort versionPersistencePort;
    public VersionUseCase(IVersionPersistencePort versionPersistencePort) {
        this.versionPersistencePort = versionPersistencePort;
    }
    @Override
    public void saveVersion(Version version) {
        if(version.getInitialDate().isAfter(version.getEndDate())){
            throw new IncorrectEndDateException();
        }
        if(version.getInitialDate().isBefore(LocalDate.now())){
            throw new IncorrectInitialDateException();
        }
        versionPersistencePort.saveVersion(version);
    }
}
