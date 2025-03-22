package com.clinica.proyect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Long id;
    private Long doctor_id;
    private Long patient_id;
    private LocalDateTime schedule;
    private String status;

    public AppointmentDTO(Long doctor_id, Long patient_id, LocalDateTime schedule, String status) {
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.schedule = schedule;
        this.status = status;
    }
}
