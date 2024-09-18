package com.kosmos.hospital.repository;

import com.kosmos.hospital.model.ConsultoriosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultoriosRepository extends JpaRepository<ConsultoriosModel, Long> {
    // Se pueden agregar consultas personalizadas aquí si es necesario
}