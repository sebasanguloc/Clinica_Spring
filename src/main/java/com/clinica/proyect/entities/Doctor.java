package com.clinica.proyect.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @Size(min = 1,max = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "specailty_id",nullable = false)
    private Specialty specialty;

    @Column(nullable = false)
    private LocalTime scheduleStart;

    @Column(nullable = false)
    private LocalTime scheduleEnd;

    public Doctor(LocalTime scheduleEnd, LocalTime scheduleStart, Specialty specialty, String lastname, String name) {
        this.scheduleEnd = scheduleEnd;
        this.scheduleStart = scheduleStart;
        this.specialty = specialty;
        this.lastname = lastname;
        this.name = name;
    }
}

