package com.ganesh.loans.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BaseEntity {
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
}
