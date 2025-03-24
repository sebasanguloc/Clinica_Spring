package com.clinica.proyect.controllers;

import com.clinica.proyect.Exceptions.ValidationException;
import com.clinica.proyect.dto.DoctorDTO;
import com.clinica.proyect.entities.Doctor;
import com.clinica.proyect.services.IDoctorService;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final IDoctorService doctorService;

    public DoctorController(IDoctorService IDoctorService) {
        this.doctorService = IDoctorService;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        List<Doctor> doctors = doctorService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id){
        Doctor doctor = doctorService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(doctor);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody @Valid DoctorDTO doctor, BindingResult result){
        if(result.hasErrors()){
            throw new ValidationException("Error in Doctor entity", result);
        }

        Doctor newDoctor = doctorService.create(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDoctor);
    }

}
