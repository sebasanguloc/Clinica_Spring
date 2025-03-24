package com.clinica.proyect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {

    private Long id;

    @Size(min = 1, max = 100, message = "The name field must be between 1 and 100 characters.")
    private String name;

    @Size(min = 1,max = 100, message = "The lastname field must be between 1 and 100 characters.")
    private String lastname;

    @NotNull(message = "The specialty field cannot be null")
    private Long specialtyId;

    @NotNull(message = "The schedule_start field cannot be null")
    private LocalTime scheduleStart;

    @NotNull(message = "The schedule_end field cannot be null")
    private LocalTime scheduleEnd;

    public DoctorDTO(String name, String lastname, Long specialtyId, LocalTime scheduleEnd, LocalTime scheduleStart) {
        this.name = name;
        this.lastname = lastname;
        this.specialtyId = specialtyId;
        this.scheduleEnd = scheduleEnd;
        this.scheduleStart = scheduleStart;
    }
}
