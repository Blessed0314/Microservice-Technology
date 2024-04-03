package com.example.on_class.adapters.driving.http.mapper;

import com.example.on_class.adapters.driving.http.dto.response.BootcampResponse;
import com.example.on_class.adapters.driving.http.dto.response.CapacityToBootcampResponse;
import com.example.on_class.adapters.driving.http.dto.response.TechnologyToCapacityResponse;
import com.example.on_class.domain.model.Bootcamp;
import com.example.on_class.domain.model.Capacity;
import com.example.on_class.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBootcampResponseMapper {

    @Mapping(target = "capacities", source = "bootcamp.capacities")
    BootcampResponse toBootcampResponse(Bootcamp bootcamp);
    List<BootcampResponse> toBootcampResponseList(List<Bootcamp> bootcamps);

    @Mapping(target = "technologies", source = "capacity.technologies")
    CapacityToBootcampResponse toCapacityResponse(Capacity capacity);
    List<CapacityToBootcampResponse> toCapacityResponseList(List<Capacity> capacities);

    List<TechnologyToCapacityResponse> toTechnologyResponseList(List<Technology> technologies);
    TechnologyToCapacityResponse toTechnologyResponse(Technology technology);
}
