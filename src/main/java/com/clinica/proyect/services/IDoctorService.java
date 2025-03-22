package com.clinica.proyect.services;

import com.clinica.proyect.entities.Doctor;

import java.util.List;
import java.util.Optional;

public interface IDoctorService {

    List<Doctor> findAll();

    Optional<Doctor> findById(Long id);

    Doctor create(Doctor create);

    Doctor update(Doctor doctor);

    Doctor delete(Long id);

}
