package com.example.on_class.adapters.driving.http.mapper;

import com.example.on_class.adapters.driving.http.dto.response.CapacityResponse;
import com.example.on_class.adapters.driving.http.dto.response.TechnologyToCapacityResponse;
import com.example.on_class.domain.model.Capacity;
import com.example.on_class.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICapacityResponseMapper {

    @Mapping(target = "technologies", source = "capacity.technologies")
    CapacityResponse toCapacityResponse(Capacity capacity);
    List<CapacityResponse> toCapacityResponseList(List<Capacity> capacities);


    List<TechnologyToCapacityResponse> toTechnologyResponseList(List<Technology> technologies);
    TechnologyToCapacityResponse toTechnologyResponse(Technology technology);
}