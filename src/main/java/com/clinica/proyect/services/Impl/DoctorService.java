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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class DoctorService implements IDoctorService {

    private final IDoctorRepository doctorRepository;
    private final ISpecialtyService specialtyService;

    public DoctorService(IDoctorRepository doctorRepository, ISpecialtyService specialtyService) {
        this.doctorRepository = doctorRepository;
        this.specialtyService = specialtyService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Doctor findById(Long id) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(id);
        return doctorOpt.orElseThrow(() -> new EntityNotFoundException("Doctor Not Found"));
    }

    @Transactional
    @Override
    public Doctor create(DoctorDTO doctor) {
        Specialty specialty = specialtyService.findById(doctor.getSpecialtyId());

        System.out.println(specialty);
        Doctor newDoctor = new Doctor(
                doctor.getName().toLowerCase(),
                doctor.getLastname().toLowerCase(),
                specialty,
                doctor.getScheduleStart(),
                doctor.getScheduleEnd()
                );
        return doctorRepository.save(newDoctor);
    }

    @Transactional
    @Override
    public Doctor update(DoctorDTO doctor) {
        Doctor doctorDb = findById(doctor.getId());
        Specialty specialty = specialtyService.findById(doctor.getSpecialtyId());

        doctorDb.setName(doctor.getName().toLowerCase());
        doctorDb.setLastname(doctor.getLastname().toLowerCase());
        doctorDb.setSpecialty(specialty);
        doctorDb.setScheduleStart(doctor.getScheduleStart());
        doctorDb.setScheduleEnd(doctor.getScheduleEnd());

        return doctorRepository.save(doctorDb);
    }

    @Transactional
    @Override
    public Doctor partialUpdate(Long id, Map<String, Object> updates) {
        Doctor doctorDb = findById(id);
        updates.forEach((key,value) -> {
            switch (key){
                case "name":
                    doctorDb.setName(((String) value).toLowerCase());
                    break;
                case "lastname":
                    doctorDb.setLastname(((String) value).toLowerCase());
                    break;
                case "specialtyId":
                    Specialty specialty = specialtyService.findById((Long) value);
                    doctorDb.setSpecialty(specialty);
                    break;
                case "scheduleStart":
                    doctorDb.setScheduleStart((LocalTime) value);
                    break;
                case "scheduleEnd":
                    doctorDb.setScheduleEnd((LocalTime) value);
                    break;
            }
        });
        return doctorRepository.save(doctorDb);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if(!doctorRepository.existsById(id)){
            throw new EntityNotFoundException("Doctor not found");
        } else {
            doctorRepository.deleteById(id);
        }
    }
}
