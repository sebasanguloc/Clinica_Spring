package com.clinica.proyect.services;

import com.clinica.proyect.dto.DoctorDTO;
import com.clinica.proyect.entities.Doctor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IDoctorService {

    List<Doctor> findAll();

    Doctor findById(Long id);

    Doctor create(DoctorDTO doctor);

    Doctor update(DoctorDTO doctor);

    Doctor partialUpdate(Long id, Map<String, Object> updates);

    void delete(Long id);

}
