package com.example.on_class.adapters.driving.http.mapper;

import com.example.on_class.adapters.driving.http.dto.request.AddVersionRequest;
import com.example.on_class.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IVersionRequestMapper {
    @Mapping(target = "id", ignore = true)
    Version addRequestVersion(AddVersionRequest addVersionRequest);
}
