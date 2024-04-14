package com.example.on_class.adapters.driving.http.controller;

import com.example.on_class.adapters.driving.http.dto.request.AddBootcampRequest;
import com.example.on_class.adapters.driving.http.dto.response.BootcampResponse;
import com.example.on_class.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.example.on_class.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.example.on_class.domain.api.IBootcampServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
public class BootcampRestControllerAdapter {
    private final IBootcampServicePort bootcampServicePort;
    private final IBootcampRequestMapper bootcampRequestMapper;
    private final IBootcampResponseMapper bootcampResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addBootcamp(@Valid @RequestBody AddBootcampRequest request){
        bootcampServicePort.saveBootcamp(bootcampRequestMapper.addRequestToBootcamp(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<BootcampResponse>> getBootcampList(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "false") boolean orderFlag,
            @RequestParam(defaultValue = "true") boolean ascendingFlag){
        return ResponseEntity.ok(bootcampResponseMapper.toBootcampResponseList(bootcampServicePort.getAllBootcamps(page, size, orderFlag, ascendingFlag)));
    }
}
