package com.example.on_class.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CapacityToBootcampResponse {
    private final Long id;
    private final String name;
    private final List<TechnologyToCapacityResponse> technologies;
}
