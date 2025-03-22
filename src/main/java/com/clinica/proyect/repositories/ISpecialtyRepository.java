package com.clinica.proyect.repositories;

import com.clinica.proyect.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialtyRepository extends JpaRepository<Specialty,Long> {
}
