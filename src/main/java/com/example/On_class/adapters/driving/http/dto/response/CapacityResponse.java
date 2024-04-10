package com.example.On_class.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CapacityResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final List<TechnologyToCapacityResponse> technologies;
}
