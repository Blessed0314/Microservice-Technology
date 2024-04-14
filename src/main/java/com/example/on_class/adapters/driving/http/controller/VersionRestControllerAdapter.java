package com.example.on_class.adapters.driving.http.controller;

import com.example.on_class.adapters.driving.http.dto.request.AddVersionRequest;
import com.example.on_class.adapters.driving.http.dto.response.VersionResponse;
import com.example.on_class.adapters.driving.http.mapper.IVersionRequestMapper;
import com.example.on_class.adapters.driving.http.mapper.IVersionResponseMapper;
import com.example.on_class.domain.api.IVersionServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/version")
@RequiredArgsConstructor
public class VersionRestControllerAdapter {
    private final IVersionServicePort versionServicePort;
    private final IVersionRequestMapper versionRequestMapper;
    private final IVersionResponseMapper versionResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addVersion(@Valid @RequestBody AddVersionRequest request){
        versionServicePort.saveVersion(versionRequestMapper.addRequestVersion(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<VersionResponse>> getVersionList(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "0") Integer orderFlag,
            @RequestParam(defaultValue = "true") boolean ascendingFlag){
        return ResponseEntity.ok(versionResponseMapper.toVersionResponseList(versionServicePort.getAllVersions(null, page, size, orderFlag, ascendingFlag)));
    }

    @GetMapping("/{bootcamp}")
    public ResponseEntity<List<VersionResponse>> getVersionList(
            @PathVariable(required = false, name = "bootcamp") String bootcamp,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "0") Integer orderFlag,
            @RequestParam(defaultValue = "true") boolean ascendingFlag){
        return ResponseEntity.ok(versionResponseMapper.toVersionResponseList(versionServicePort.getAllVersions(bootcamp, page, size, orderFlag, ascendingFlag)));
    }
}
