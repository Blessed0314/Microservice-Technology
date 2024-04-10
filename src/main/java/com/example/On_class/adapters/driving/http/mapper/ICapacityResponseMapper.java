package com.example.On_class.adapters.driving.http.mapper;

import com.example.On_class.adapters.driving.http.dto.response.CapacityResponse;
import com.example.On_class.domain.model.Capacity;
import org.mapstruct.Mapper;
import java.util.List;


@Mapper(componentModel = "spring")
public interface ICapacityResponseMapper {
    List<CapacityResponse> toCapacityResponseList(List<Capacity> capacities);
}
