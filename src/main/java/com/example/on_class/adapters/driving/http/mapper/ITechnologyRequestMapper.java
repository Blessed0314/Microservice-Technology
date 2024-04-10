package com.example.on_class.adapters.driving.http.mapper;

import com.example.on_class.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.example.on_class.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ITechnologyRequestMapper {
    @Mapping(target = "id", ignore = true )
    Technology addRequestToTechnology(AddTechnologyRequest addTechnologyRequest);
}
