package com.example.on_class.domain.api;

import com.example.on_class.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampServicePort {
    void saveBootcamp(Bootcamp bootcamp);
    List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean orderFlag, boolean ascendingFlag);
}
