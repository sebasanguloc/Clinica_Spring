package com.clinica.proyect.controllers;

import com.clinica.proyect.entities.Patient;
import com.clinica.proyect.exceptions.ValidationException;
import com.clinica.proyect.services.IPatientService;
import com.clinica.proyect.services.Impl.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients(){
        List<Patient> patients = patientService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
        Patient patient = patientService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody @Valid Patient patient, BindingResult result){
        if(result.hasErrors()){
            throw new ValidationException("Error creating Patient entity", result);
        }

        Patient newPatient = patientService.create(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody @Valid Patient patient, BindingResult result){
        if(result.hasErrors()){
            throw new ValidationException("Error updating Patient entity", result);
        }
        patient.setId(id);
        Patient newPatient = patientService.update(patient);
        return ResponseEntity.status(HttpStatus.OK).body(newPatient);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Patient> patchPatient(@PathVariable Long id, @RequestBody Map<String,Object> updates){
        Patient patient = patientService.partialUpdates(id, updates);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id){
        patientService.delete(id);
        Map<String, String> json = new HashMap<>();
        json.put("message","Patient deleted succesfully");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }
}
