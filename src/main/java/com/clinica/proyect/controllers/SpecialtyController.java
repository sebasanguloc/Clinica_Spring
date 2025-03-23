package com.clinica.proyect.controllers;

import com.clinica.proyect.Exceptions.ValidationException;
import com.clinica.proyect.entities.Specialty;
import com.clinica.proyect.services.ISpecialtyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

    private final ISpecialtyService specialtyService;

    public SpecialtyController(ISpecialtyService ISpecialtyService) {
        this.specialtyService = ISpecialtyService;
    }

    @GetMapping
    public ResponseEntity<List<Specialty>> getAllSpecialties(){
        List<Specialty> specialties = specialtyService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(specialties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialty> getSpecialtyById(@PathVariable Long id){
        Specialty specialty = specialtyService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(specialty);
    }

    @PostMapping
    public ResponseEntity<Specialty> createSpecialty(@RequestBody @Valid Specialty specialty, BindingResult result){
        Specialty newSpecialty = specialtyService.create(specialty);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSpecialty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specialty> updateSpecialty(@PathVariable Long id, @RequestBody @Valid Specialty specialty, BindingResult result){
        if(result.hasErrors()) {
            throw new ValidationException("Error in Specialty entity", result);
        }
        specialty.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(specialtyService.update(specialty));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpecialty(@PathVariable Long id){
        specialtyService.delete(id);
        Map<String, String> json = new HashMap<>();
        json.put("message","Specialty deleted succesfully");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }
}
