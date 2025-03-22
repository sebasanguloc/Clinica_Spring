package com.clinica.proyect.services;

import com.clinica.proyect.entities.Appointment;

import java.util.List;
import java.util.Optional;

public interface IAppointmentService {

    List<Appointment> findAll();

    Optional<Appointment> findById();

    Appointment update(Appointment appointment);

    Appointment delete(Long id);
}
