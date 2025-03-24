package com.clinica.proyect.services.Impl;

import com.clinica.proyect.dto.DoctorDTO;
import com.clinica.proyect.entities.Doctor;
import com.clinica.proyect.entities.Specialty;
import com.clinica.proyect.repositories.IDoctorRepository;
import com.clinica.proyect.services.IDoctorService;
import com.clinica.proyect.services.ISpecialtyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService implements IDoctorService {

    private final IDoctorRepository doctorRepository;
    private final ISpecialtyService specialtyService;

    public DoctorService(IDoctorRepository doctorRepository, ISpecialtyService specialtyService) {
        this.doctorRepository = doctorRepository;
        this.specialtyService = specialtyService;
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findById(Long id) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(id);
        return doctorOpt.orElseThrow(() -> new EntityNotFoundException("Doctor Not Found"));
    }

    @Override
    public Doctor create(DoctorDTO doctor) {
        Specialty specialty = specialtyService.findById(doctor.getSpecialtyId());

        System.out.println(specialty);
        Doctor newDoctor = new Doctor(
                doctor.getName(),
                doctor.getLastname(),
                specialty,
                doctor.getScheduleStart(),
                doctor.getScheduleEnd()
                );
        return doctorRepository.save(newDoctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
