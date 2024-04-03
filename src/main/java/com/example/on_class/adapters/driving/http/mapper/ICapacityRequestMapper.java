package com.example.on_class.adapters.driving.http.mapper;

import com.example.on_class.adapters.driving.http.dto.request.AddCapacityRequest;
import com.example.on_class.domain.model.Capacity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICapacityRequestMapper {
    @Mapping(target = "id", ignore = true )
    Capacity addRequestToCapacity(AddCapacityRequest addCapacityRequest);
}
