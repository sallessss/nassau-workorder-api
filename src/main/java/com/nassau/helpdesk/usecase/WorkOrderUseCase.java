package com.nassau.helpdesk.usecase;

import com.nassau.helpdesk.entrypoint.dto.CreateWorkOrderRequestDto;
import com.nassau.helpdesk.entrypoint.dto.UpdateWorkOrderRequestDto;
import com.nassau.helpdesk.entrypoint.dto.WorkOrderResponseDto;

import java.util.List;

public interface WorkOrderUseCase {

    WorkOrderResponseDto create(CreateWorkOrderRequestDto requestDto);

    WorkOrderResponseDto findById(Long id);

    List<WorkOrderResponseDto> findAll();

    WorkOrderResponseDto update(Long id, UpdateWorkOrderRequestDto requestDto);

    void delete(Long id);
}
