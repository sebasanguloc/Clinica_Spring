package com.clinica.proyect.services.Impl;

import com.clinica.proyect.entities.Specialty;
import com.clinica.proyect.repositories.ISpecialtyRepository;
import com.clinica.proyect.services.ISpecialtyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpecialtyService implements ISpecialtyService {


    private final ISpecialtyRepository specialtyRepository;

    public SpecialtyService(ISpecialtyRepository ISpecialtyRepository) {
        this.specialtyRepository = ISpecialtyRepository;
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }

    @Override
    public Specialty findById(Long id) {
        Optional<Specialty> specialty = specialtyRepository.findById(id);
        return specialty.orElseThrow(() -> new EntityNotFoundException("Specialty Not Found"));
    }

    @Override
    public Specialty create(Specialty specialty) {
        specialty.setName(specialty.getName().toLowerCase());
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {
        Specialty specialtyDB = findById(specialty.getId());
        if(specialtyDB == null ) throw new EntityNotFoundException("Specialty not found");
        specialtyDB.setName(specialty.getName().toLowerCase());
        return specialtyRepository.save(specialtyDB);
    }

    @Override
    public void delete(Long id) {
        if(!specialtyRepository.existsById(id)){
            throw new EntityNotFoundException("Specialty not found");
        }else {
            specialtyRepository.deleteById(id);
        }
    }
}
