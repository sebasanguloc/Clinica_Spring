package com.clinica.proyect.repositories;

import com.clinica.proyect.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment,Long> {
}
