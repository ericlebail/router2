package com.github.mateuszrasinski.recruitment.router.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Event {
    @Id
    private Long id;
    private String code;
    private EventStatus status;
    private LocalDate date;
    private String userCode;
    private String userUuid;
    private String payload;
}
