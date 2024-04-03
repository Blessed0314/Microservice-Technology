package com.example.on_class.adapters.driven.jpa.mysql.mapper;

import com.example.on_class.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.example.on_class.domain.model.Technology;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyEntityMapper {
    TechnologyEntity toEntity(Technology technology);
    Technology toModel(TechnologyEntity entity);
    List<Technology> toModelList(List<TechnologyEntity> technologyEntities);
}
