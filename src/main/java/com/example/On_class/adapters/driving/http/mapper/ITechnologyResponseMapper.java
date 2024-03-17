package com.example.On_class.adapters.driving.http.mapper;

import com.example.On_class.adapters.driving.http.dto.response.TechnologyResponse;
import com.example.On_class.domain.model.Technology;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITechnologyResponseMapper {
    TechnologyResponse toTechnologyResponse(Technology technology);
}
