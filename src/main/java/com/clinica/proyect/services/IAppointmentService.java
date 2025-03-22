package com.clinica.proyect.services;

import com.clinica.proyect.entities.Appointment;

import java.util.List;
import java.util.Optional;

public interface IAppointmentService {

    List<Appointment> findAll();

    Optional<Appointment> findById(Long id);

    Appointment create(Appointment appointment);

    Appointment update(Appointment appointment);

    Boolean delete(Long id);
}
