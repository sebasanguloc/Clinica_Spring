package com.clinica.proyect.controllers;

import com.clinica.proyect.exceptions.ValidationException;
import com.clinica.proyect.dto.DoctorDTO;
import com.clinica.proyect.entities.Doctor;
import com.clinica.proyect.services.IDoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            throw new ValidationException("Error creating Doctor entity", result);
        }
        Doctor newDoctor = doctorService.create(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDoctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody @Valid DoctorDTO doctor, BindingResult result){
        if(result.hasErrors()){
            throw new ValidationException("Error updating Doctor entity", result);
        }
        doctor.setId(id);
        Doctor newDoctor = doctorService.update(doctor);
        return ResponseEntity.status(HttpStatus.OK).body(newDoctor);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Doctor> patchDoctor(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        Doctor doctor = doctorService.partialUpdate(id,updates);
        return ResponseEntity.status(HttpStatus.OK).body(doctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id){
        doctorService.delete(id);
        Map<String, String> json = new HashMap<>();
        json.put("message","Doctor deleted succesfully");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

}
