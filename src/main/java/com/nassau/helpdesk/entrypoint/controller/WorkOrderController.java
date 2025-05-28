package com.nassau.helpdesk.entrypoint.controller;

import com.nassau.helpdesk.entrypoint.dto.CreateWorkOrderRequestDto;
import com.nassau.helpdesk.entrypoint.dto.UpdateWorkOrderRequestDto;
import com.nassau.helpdesk.entrypoint.dto.WorkOrderResponseDto;
import com.nassau.helpdesk.usecase.WorkOrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workorders/")
@RequiredArgsConstructor
public class WorkOrderController {

    private final WorkOrderUseCase workOrderUseCase;

    @PostMapping
    public ResponseEntity<WorkOrderResponseDto> create(@RequestBody CreateWorkOrderRequestDto requestDto) {
        WorkOrderResponseDto responseDto = workOrderUseCase.create(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkOrderResponseDto> findById(@PathVariable Long id) {
        WorkOrderResponseDto responseDto = workOrderUseCase.findById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<WorkOrderResponseDto>> findAll() {
        List<WorkOrderResponseDto> list = workOrderUseCase.findAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkOrderResponseDto> update(@PathVariable Long id, @RequestBody UpdateWorkOrderRequestDto requestDto) {
        WorkOrderResponseDto responseDto = workOrderUseCase.update(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workOrderUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}