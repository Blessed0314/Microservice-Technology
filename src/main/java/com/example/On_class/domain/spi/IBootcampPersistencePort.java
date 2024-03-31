package com.example.On_class.domain.spi;

import com.example.On_class.domain.model.Bootcamp;

public interface IBootcampPersistencePort {
    void saveBootcamp(Bootcamp bootcamp);
}
