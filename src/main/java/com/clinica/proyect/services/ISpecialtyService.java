package com.clinica.proyect.services;

import com.clinica.proyect.entities.Specialty;

import java.util.List;
import java.util.Optional;

public interface ISpecialtyService {

    List<Specialty> findAll();

    Specialty findById(Long id);

    Specialty create(Specialty specialty);

    Specialty update(Specialty specialty);

    void delete(Long id);
}
