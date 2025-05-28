package com.nassau.helpdesk.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "work_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkOrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long requesterId;

    private Long responsibleId;

    private Long categoryId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum Status {
        ABERTO,
        EM_ANDAMENTO,
        COMPLETADO,
        CANCELADO
    }
}