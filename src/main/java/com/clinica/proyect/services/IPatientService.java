package com.clinica.proyect.services;

import com.clinica.proyect.entities.Patient;

import java.util.*;

public interface IPatientService {

    List<Patient> findAll();

    Patient findById(Long id);

    Patient create(Patient patient);

    Patient update(Patient patient);

    Patient partialUpdates(Long id, Map<String,Object> updates);

    void delete(Long id);

}
