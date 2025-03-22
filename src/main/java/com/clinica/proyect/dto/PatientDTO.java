package com.clinica.proyect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long id;
    private String name;
    private String lastname;
    private Integer age;
    private String phone;

    public PatientDTO(String name, String lastname, String phone, Integer age) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.age = age;
    }
}
