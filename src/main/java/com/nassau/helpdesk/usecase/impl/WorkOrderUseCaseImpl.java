package com.nassau.helpdesk.usecase.impl;

import com.nassau.helpdesk.entrypoint.dto.CreateWorkOrderRequestDto;
import com.nassau.helpdesk.entrypoint.dto.UpdateWorkOrderRequestDto;
import com.nassau.helpdesk.entrypoint.dto.WorkOrderResponseDto;
import com.nassau.helpdesk.exception.NotFoundException;
import com.nassau.helpdesk.model.WorkOrderModel;
import com.nassau.helpdesk.repository.WorkOrderRepository;
import com.nassau.helpdesk.usecase.WorkOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkOrderUseCaseImpl implements WorkOrderUseCase {

    private final WorkOrderRepository repository;

    @Override
    public WorkOrderResponseDto create(CreateWorkOrderRequestDto requestDto) {
        WorkOrderModel entity = WorkOrderModel.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .requesterId(requestDto.getRequesterId())
                .responsibleId(requestDto.getResponsibleId())
                .categoryId(requestDto.getCategoryId())
                .status(WorkOrderModel.Status.ABERTO)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        WorkOrderModel saved = repository.save(entity);

        return mapToResponseDto(saved);
    }

    @Override
    public WorkOrderResponseDto findById(Long id) {
        WorkOrderModel entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ordem de serviço não encontrada com o id: " + id));
        return mapToResponseDto(entity);
    }

    @Override
    public List<WorkOrderResponseDto> findAll() {
        return repository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public WorkOrderResponseDto update(Long id, UpdateWorkOrderRequestDto requestDto) {
        WorkOrderModel entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ordem de serviço não encontrada com o id: " + id));

        entity.setTitle(requestDto.getTitle());
        entity.setDescription(requestDto.getDescription());
        entity.setRequesterId(requestDto.getRequesterId());
        entity.setResponsibleId(requestDto.getResponsibleId());
        entity.setCategoryId(requestDto.getCategoryId());
        entity.setStatus(WorkOrderModel.Status.valueOf(requestDto.getStatus()));
        entity.setUpdatedAt(LocalDateTime.now());

        WorkOrderModel updated = repository.save(entity);

        return mapToResponseDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Ordem de serviço não encontrada com o id: " + id);
        }
        repository.deleteById(id);
    }

    private WorkOrderResponseDto mapToResponseDto(WorkOrderModel model) {
        return WorkOrderResponseDto.builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .status(model.getStatus().name())
                .requesterId(model.getRequesterId())
                .responsibleId(model.getResponsibleId())
                .categoryId(model.getCategoryId())
                .createdAt(model.getCreatedAt())
                .updatedAt(model.getUpdatedAt())
                .build();
    }
}