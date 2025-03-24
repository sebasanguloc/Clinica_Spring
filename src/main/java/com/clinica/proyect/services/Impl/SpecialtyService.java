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

    @Transactional(readOnly = true)
    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Specialty findById(Long id) {
        Optional<Specialty> specialtyOpt = specialtyRepository.findById(id);
        return specialtyOpt.orElseThrow(() -> new EntityNotFoundException("Specialty Not Found"));
    }

    @Transactional
    @Override
    public Specialty create(Specialty specialty) {
        specialty.setName(specialty.getName().toLowerCase());
        return specialtyRepository.save(specialty);
    }

    @Transactional
    @Override
    public Specialty update(Specialty specialty) {
        Specialty specialtyDB = findById(specialty.getId());
        if(specialtyDB == null ) throw new EntityNotFoundException("Specialty not found");
        specialtyDB.setName(specialty.getName().toLowerCase());
        return specialtyRepository.save(specialtyDB);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if(!specialtyRepository.existsById(id)){
            throw new EntityNotFoundException("Specialty not found");
        }else {
            specialtyRepository.deleteById(id);
        }
    }
}
