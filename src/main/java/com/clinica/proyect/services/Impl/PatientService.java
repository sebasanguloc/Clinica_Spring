package com.clinica.proyect.services.Impl;

import com.clinica.proyect.entities.Patient;
import com.clinica.proyect.repositories.IPatientRepository;
import com.clinica.proyect.services.IPatientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    private final IPatientRepository patientRepository;

    public PatientService(IPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Patient not found"));
    }

    @Transactional
    @Override
    public Patient create(Patient patient) {
        patient.setName(patient.getName().toLowerCase());
        patient.setLastname(patient.getLastname().toLowerCase());
        return patientRepository.save(patient);
    }

    @Transactional
    @Override
    public Patient update(Patient patient) {
        Patient patientDb = findById(patient.getId());

        patientDb.setName(patient.getName().toLowerCase());
        patientDb.setLastname(patient.getLastname().toLowerCase());
        patientDb.setAge(patient.getAge());
        patientDb.setPhone(patient.getPhone());

        return patientRepository.save(patientDb);
    }

    @Override
    public Patient partialUpdates(Long id, Map<String, Object> updates) {
        Patient patientDb = findById(id);

        updates.forEach((key,value) -> {
            switch(key){
                case "name":
                    patientDb.setName(((String) value).toLowerCase());
                    break;
                case "lastname":
                    patientDb.setLastname(((String) value).toLowerCase());
                    break;
                case "age":
                    patientDb.setAge((Integer) value);
                    break;
                case "phone":
                    patientDb.setPhone((String) value);
            }
        });
        return patientRepository.save(patientDb);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if(!patientRepository.existsById(id)){
            throw new EntityNotFoundException("Patient not found");
        }else {
            patientRepository.deleteById(id);
        }
    }
}
