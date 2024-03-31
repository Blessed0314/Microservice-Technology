package com.example.On_class.domain.api.usecase;

import com.example.On_class.domain.api.IBootcampServicePort;
import com.example.On_class.domain.model.Bootcamp;
import com.example.On_class.domain.spi.IBootcampPersistencePort;
import com.example.On_class.domain.spi.ICapacityPersistencePort;

public class BootcampUseCase implements IBootcampServicePort {
    private IBootcampPersistencePort bootcampPersistencePort;
    public BootcampUseCase(IBootcampPersistencePort bootcampPersistencePort){
        this.bootcampPersistencePort = bootcampPersistencePort;
    }
    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        bootcampPersistencePort.saveBootcamp(bootcamp);
    }
}
