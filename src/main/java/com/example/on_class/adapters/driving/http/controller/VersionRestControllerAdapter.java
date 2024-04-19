package com.example.on_class.adapters.driving.http.controller;

import com.example.on_class.adapters.driving.http.dto.request.AddVersionRequest;
import com.example.on_class.adapters.driving.http.mapper.IVersionRequestMapper;
import com.example.on_class.domain.api.IVersionServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/version")
@RequiredArgsConstructor
public class VersionRestControllerAdapter {
    private final IVersionServicePort versionServicePort;
    private final IVersionRequestMapper versionRequestMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addVersion(@Valid @RequestBody AddVersionRequest request){
        versionServicePort.saveVersion(versionRequestMapper.addRequestVersion(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
