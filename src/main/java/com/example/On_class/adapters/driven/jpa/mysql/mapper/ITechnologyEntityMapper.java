package com.example.On_class.adapters.driven.jpa.mysql.mapper;

import com.example.On_class.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.example.On_class.domain.model.Technology;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITechnologyEntityMapper {
    TechnologyEntity toEntity(Technology technology);
}
