package com.kosmos.hospital.repository;

import com.kosmos.hospital.model.CitasModel;
import com.kosmos.hospital.model.ConsultoriosModel;
import com.kosmos.hospital.model.DoctoresModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitasRepository extends JpaRepository<CitasModel, Long> {
    List<CitasModel> findByHorarioConsultaBetween(LocalDateTime start, LocalDateTime end);
    List<CitasModel> findByConsultoriosModelAndHorarioConsulta(ConsultoriosModel consultorio, LocalDateTime horario);
    List<CitasModel> findByDoctoresModelAndHorarioConsulta(DoctoresModel doctor, LocalDateTime horario);
    List<CitasModel> findByNombrePacienteAndHorarioConsultaBetween(String nombrePaciente, LocalDateTime start, LocalDateTime end);
}