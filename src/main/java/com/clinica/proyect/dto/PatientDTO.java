package com.clinica.proyect.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String lastname;

    @NotNull
    @NotBlank
    @Min(1)
    @Max(150)
    private Integer age;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String phone;

    public PatientDTO(String name, String lastname, String phone, Integer age) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.age = age;
    }
}
