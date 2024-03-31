package com.example.On_class.adapters.driving.http.controller;

import com.example.On_class.adapters.driving.http.dto.request.AddBootcampRequest;
import com.example.On_class.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.example.On_class.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.example.On_class.domain.api.IBootcampServicePort;
import com.example.On_class.domain.api.ICapacityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
public class BootcampRestControllerAdapter {
    private final IBootcampServicePort bootcampServicePort;
    private final IBootcampRequestMapper bootcampRequestMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addBootcamp(@Valid @RequestBody AddBootcampRequest request){
        bootcampServicePort.saveBootcamp(bootcampRequestMapper.addRequestToBootcamp(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
