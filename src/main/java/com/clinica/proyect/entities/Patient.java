package com.clinica.proyect.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false,length = 100)
    @Size(min = 1,max = 100)
    private String lastname;

    @Min(1)
    @Max(150)
    private Integer age;

    @Column(nullable = false, length = 20, unique = true)
    @NotBlank
    private String phone;

    public Patient(String name, String lastname, Integer age, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.phone = phone;
    }
}
