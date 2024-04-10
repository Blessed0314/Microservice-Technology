package com.example.on_class.adapters.driving.http.mapper;

import com.example.on_class.adapters.driving.http.dto.response.TechnologyResponse;
import com.example.on_class.domain.model.Technology;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyResponseMapper {
    List<TechnologyResponse> toTechnologyResponseList(List<Technology> technologies);
}
