package com.clinica.proyect.services;

import com.clinica.proyect.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {

    List<Patient> findAll();

    Optional<Patient> findById(Long id);

    Patient create(Patient patient);

    Patient update(Patient patient);

    Boolean delete(Long id);

}
