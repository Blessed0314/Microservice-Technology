package com.example.on_class.adapters.driving.http.controller;

import com.example.on_class.adapters.driving.http.dto.request.AddCapacityRequest;
import com.example.on_class.adapters.driving.http.dto.response.CapacityResponse;
import com.example.on_class.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.example.on_class.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.example.on_class.domain.api.ICapacityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/capacity")
@RequiredArgsConstructor
public class CapacityRestControllerAdapter {
    private final ICapacityServicePort capacityServicePort;
    private final ICapacityRequestMapper capacityRequestMapper;
    private final ICapacityResponseMapper capacityResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addCapacity(@Valid @RequestBody AddCapacityRequest request){
        capacityServicePort.saveCapacity(capacityRequestMapper.addRequestToCapacity(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CapacityResponse>> getCapacityList(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "false") boolean orderFlag,
            @RequestParam(defaultValue = "true") boolean ascendingFlag){
        return ResponseEntity.ok(capacityResponseMapper.toCapacityResponseList(capacityServicePort.getAllCapacities(page, size, orderFlag, ascendingFlag)));
    }
}
