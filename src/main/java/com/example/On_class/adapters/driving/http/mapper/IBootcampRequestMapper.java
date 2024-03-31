package com.example.On_class.adapters.driving.http.mapper;

import com.example.On_class.adapters.driving.http.dto.request.AddBootcampRequest;
import com.example.On_class.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IBootcampRequestMapper {
    @Mapping(target = "id", ignore = true)
    Bootcamp addRequestToBootcamp(AddBootcampRequest addBootcampRequest);
}
