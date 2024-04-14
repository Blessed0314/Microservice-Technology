package com.example.on_class.domain.model;

import java.time.LocalDate;

public class Version {
    private final Long id;
    private final int quota;
    private final LocalDate initialDate;
    private final LocalDate endDate;
    private final Bootcamp bootcamp;


    public Version(Long id, int quota, LocalDate initialDate, LocalDate endDate, Bootcamp bootcamp) {
        this.id = id;
        this.quota = quota;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.bootcamp = bootcamp;
    }

    public Long getId() {
        return id;
    }

    public int getQuota() {
        return quota;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Bootcamp getBootcamp() {
        return bootcamp;
    }
}
