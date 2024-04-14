package com.example.on_class.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class VersionResponse {
    private final Long id;
    private final int quota;
    private final LocalDate initialDate;
    private final LocalDate endDate;
    private final BootcampResponse bootcamp;
}
