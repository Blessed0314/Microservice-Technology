package com.example.On_class.adapters.driving.http.mapper;

import com.example.On_class.adapters.driving.http.dto.response.CapacityResponse;
import com.example.On_class.adapters.driving.http.dto.response.TechnologyToCapacityResponse;
import com.example.On_class.domain.model.Capacity;
import com.example.On_class.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ICapacityResponseMapper {
    @Mapping(target = "technologies", source = "capacity.technologies", qualifiedByName = "mapTechnologies")
    CapacityResponse toCapacityResponse(Capacity capacity);

    @Named("mapTechnologies")
    default List<TechnologyToCapacityResponse> mapTechnologies(List<Technology> technologies) {
        return technologies.stream()
                .map(this::mapTechnology)
                .toList();
    }

    default TechnologyToCapacityResponse mapTechnology(Technology technology) {
        return new TechnologyToCapacityResponse(technology.getId(), technology.getName());
    }

    List<CapacityResponse> toCapacityResponseList(List<Capacity> capacities);
}