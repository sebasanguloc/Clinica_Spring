package com.clinica.proyect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private Long id;
    private String lastname;
    private Long specialty;
    private LocalDateTime scheduleStart;
    private LocalDateTime scheduleEnd;

    public DoctorDTO(String lastname, Long specialty, LocalDateTime scheduleEnd, LocalDateTime scheduleStart) {
        this.lastname = lastname;
        this.specialty = specialty;
        this.scheduleEnd = scheduleEnd;
        this.scheduleStart = scheduleStart;
    }
}
