package com.example.on_class.domain.api.usecase;

import com.example.on_class.domain.api.IVersionServicePort;
import com.example.on_class.domain.exception.IncorrectEndDateException;
import com.example.on_class.domain.exception.IncorrectInitialDateException;
import com.example.on_class.domain.model.Version;
import com.example.on_class.domain.spi.IVersionPersistencePort;

import java.time.LocalDate;
import java.util.List;

public class VersionUseCase implements IVersionServicePort {
    private IVersionPersistencePort versionPersistencePort;
    public VersionUseCase(IVersionPersistencePort versionPersistencePort) {
        this.versionPersistencePort = versionPersistencePort;
    }
    @Override
    public void saveVersion(Version version) {
        if(version.getInitialDate().isBefore(LocalDate.now())){
            throw new IncorrectInitialDateException();
        }
        if(version.getInitialDate().isAfter(version.getEndDate())){
            throw new IncorrectEndDateException();
        }
        versionPersistencePort.saveVersion(version);
    }

    @Override
    public List<Version> getAllVersions(String bootcamp, Integer page, Integer size, Integer orderFlag, boolean ascendingFlag) {
        return versionPersistencePort.getAllVersions(bootcamp, page, size, orderFlag, ascendingFlag);
    }
}
