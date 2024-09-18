package com.kosmos.hospital.repository;

import com.kosmos.hospital.model.DoctoresModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctoresRepository extends JpaRepository<DoctoresModel, Long> {
    // Se pueden agregar consultas personalizadas aquí si es necesario
}