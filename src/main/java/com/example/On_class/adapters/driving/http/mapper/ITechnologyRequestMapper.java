package com.example.On_class.adapters.driving.http.mapper;

import com.example.On_class.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.example.On_class.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ITechnologyRequestMapper {
    @Mapping(target = "id", ignore = true )
    Technology addRequestToTechnology(AddTechnologyRequest addTechnologyRequest);
}
