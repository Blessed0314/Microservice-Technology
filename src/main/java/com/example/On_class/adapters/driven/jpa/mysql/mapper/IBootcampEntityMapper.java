package com.example.On_class.adapters.driven.jpa.mysql.mapper;

import com.example.On_class.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.example.On_class.domain.model.Bootcamp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBootcampEntityMapper {
    BootcampEntity toEntity(Bootcamp bootcamp);
    Bootcamp toModel(BootcampEntity entity);
}
