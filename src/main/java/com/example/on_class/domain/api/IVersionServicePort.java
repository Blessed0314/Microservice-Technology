package com.example.on_class.domain.api;

import com.example.on_class.domain.model.Version;

import java.util.List;

public interface IVersionServicePort {
    void saveVersion(Version version);
    List<Version> getAllVersions(String bootcamp, Integer page, Integer size, Integer orderFlag, boolean ascendingFlag);
}
