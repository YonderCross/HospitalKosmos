package com.kosmos.hospital.service;

import com.kosmos.hospital.model.CitasModel;
import com.kosmos.hospital.model.ConsultoriosModel;
import com.kosmos.hospital.model.DoctoresModel;
import com.kosmos.hospital.repository.CitasRepository;
import com.kosmos.hospital.repository.ConsultoriosRepository;
import com.kosmos.hospital.repository.DoctoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitasService {

    @Autowired
    private CitasRepository citasRepository;

    @Autowired
    private DoctoresRepository doctoresRepository;

    @Autowired
    private ConsultoriosRepository consultoriosRepository;

    @Transactional
    public CitasModel crearCita(CitasModel cita) {
        validarCita(cita);
        return citasRepository.save(cita);
    }

    public List<CitasModel> obtenerCitasPorFecha(LocalDateTime start, LocalDateTime end) {
        return citasRepository.findByHorarioConsultaBetween(start, end);
    }

    public List<CitasModel> obtenerCitasPorConsultorioYFecha(ConsultoriosModel consultorio, LocalDateTime horario) {
        return citasRepository.findByConsultoriosModelAndHorarioConsulta(consultorio, horario);
    }

    public List<CitasModel> obtenerCitasPorDoctorYFecha(DoctoresModel doctor, LocalDateTime horario) {
        return citasRepository.findByDoctoresModelAndHorarioConsulta(doctor, horario);
    }

    public List<CitasModel> obtenerCitasPorPacienteYFecha(String nombrePaciente, LocalDateTime start, LocalDateTime end) {
        return citasRepository.findByNombrePacienteAndHorarioConsultaBetween(nombrePaciente, start, end);
    }

    @Transactional
    public void cancelarCita(Long idCita) {
        CitasModel cita = citasRepository.findById(idCita).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        citasRepository.delete(cita);
    }

    @Transactional
    public CitasModel editarCita(Long idCita, CitasModel nuevaCita) {
        CitasModel citaExistente = citasRepository.findById(idCita).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        validarCita(nuevaCita);
        citaExistente.setHorarioConsulta(nuevaCita.getHorarioConsulta());
        citaExistente.setNombrePaciente(nuevaCita.getNombrePaciente());
        citaExistente.setConsultoriosModel(nuevaCita.getConsultoriosModel());
        citaExistente.setDoctoresModel(nuevaCita.getDoctoresModel());
        return citasRepository.save(citaExistente);
    }

    private void validarCita(CitasModel cita) {
        LocalDateTime horarioConsulta = cita.getHorarioConsulta();
        ConsultoriosModel consultorio = cita.getConsultoriosModel();
        DoctoresModel doctor = cita.getDoctoresModel();

        if (citasRepository.findByConsultoriosModelAndHorarioConsulta(consultorio, horarioConsulta).size() > 0) {
            throw new RuntimeException("El consultorio ya tiene una cita en el mismo horario.");
        }

        if (citasRepository.findByDoctoresModelAndHorarioConsulta(doctor, horarioConsulta).size() > 0) {
            throw new RuntimeException("El doctor ya tiene una cita en el mismo horario.");
        }

        if (citasRepository.findByNombrePacienteAndHorarioConsultaBetween(cita.getNombrePaciente(),
                horarioConsulta.minusHours(2), horarioConsulta.plusHours(2)).size() > 0) {
            throw new RuntimeException("El paciente ya tiene una cita en el mismo horario o con menos de 2 horas de diferencia.");
        }

        if (citasRepository.findByDoctoresModelAndHorarioConsulta(doctor, horarioConsulta.toLocalDate().atStartOfDay()).size() >= 8) {
            throw new RuntimeException("El doctor no puede tener más de 8 citas en un día.");
        }
    }

    public CitasModel findCitaById(Long idCita) {
        Optional<CitasModel> citaOptional = citasRepository.findById(idCita);
        return citaOptional.orElse(null);
    }
}