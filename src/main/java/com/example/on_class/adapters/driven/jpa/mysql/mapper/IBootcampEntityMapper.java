package com.example.on_class.adapters.driven.jpa.mysql.mapper;

import com.example.on_class.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.example.on_class.domain.model.Bootcamp;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootcampEntityMapper {
    BootcampEntity toEntity(Bootcamp bootcamp);
    Bootcamp toModel(BootcampEntity entity);
    List<Bootcamp> toModelList(List<BootcampEntity> bootcampEntities);
}
