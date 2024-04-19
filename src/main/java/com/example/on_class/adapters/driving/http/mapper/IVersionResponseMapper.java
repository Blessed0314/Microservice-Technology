package com.example.on_class.adapters.driving.http.mapper;

import com.example.on_class.adapters.driving.http.dto.response.BootcampResponse;
import com.example.on_class.adapters.driving.http.dto.response.CapacityToBootcampResponse;
import com.example.on_class.adapters.driving.http.dto.response.TechnologyToCapacityResponse;
import com.example.on_class.adapters.driving.http.dto.response.VersionResponse;
import com.example.on_class.domain.model.Bootcamp;
import com.example.on_class.domain.model.Capacity;
import com.example.on_class.domain.model.Technology;
import com.example.on_class.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IVersionResponseMapper {
    @Mapping(target = "bootcamp", source = "version.bootcamp")
    VersionResponse toVersionResponse(Version version);
    List<VersionResponse> toVersionResponseList(List<Version> versions);

    @Mapping(target = "capacities", source = "bootcamp.capacities")
    BootcampResponse toBootcampResponse(Bootcamp bootcamp);

    @Mapping(target = "technologies", source = "capacity.technologies")
    CapacityToBootcampResponse toCapacityResponse(Capacity capacity);
    List<CapacityToBootcampResponse> toCapacityResponseList(List<Capacity> capacities);

    List<TechnologyToCapacityResponse> toTechnologyResponseList(List<Technology> technologies);
    TechnologyToCapacityResponse toTechnologyResponse(Technology technology);
}
