package com.kosmos.hospital.controller;

import com.kosmos.hospital.model.CitasModel;
import com.kosmos.hospital.model.ConsultoriosModel;
import com.kosmos.hospital.model.DoctoresModel;
import com.kosmos.hospital.service.CitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitasController {

    @Autowired
    private CitasService citasService;

    @PostMapping
    public ResponseEntity<CitasModel> crearCita(@RequestBody CitasModel cita) {
        CitasModel nuevaCita = citasService.crearCita(cita);
        return ResponseEntity.ok(nuevaCita);
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<CitasModel>> obtenerCitasPorFecha(@RequestParam("start") LocalDateTime start,
                                                                 @RequestParam("end") LocalDateTime end) {
        List<CitasModel> citas = citasService.obtenerCitasPorFecha(start, end);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/consultorio")
    public ResponseEntity<List<CitasModel>> obtenerCitasPorConsultorioYFecha(@RequestParam("idConsultorio") Long idConsultorio,
                                                                             @RequestParam("horario") LocalDateTime horario) {
        ConsultoriosModel consultorio = new ConsultoriosModel();
        consultorio.setIdConsultorio(idConsultorio);
        List<CitasModel> citas = citasService.obtenerCitasPorConsultorioYFecha(consultorio, horario);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<CitasModel>> obtenerCitasPorDoctorYFecha(@RequestParam("idDoctor") Long idDoctor,
                                                                        @RequestParam("horario") LocalDateTime horario) {
        DoctoresModel doctor = new DoctoresModel();
        doctor.setIdDoctor(idDoctor);
        List<CitasModel> citas = citasService.obtenerCitasPorDoctorYFecha(doctor, horario);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/paciente")
    public ResponseEntity<List<CitasModel>> obtenerCitasPorPacienteYFecha(@RequestParam("nombrePaciente") String nombrePaciente,
                                                                          @RequestParam("start") LocalDateTime start,
                                                                          @RequestParam("end") LocalDateTime end) {
        List<CitasModel> citas = citasService.obtenerCitasPorPacienteYFecha(nombrePaciente, start, end);
        return ResponseEntity.ok(citas);
    }

    @DeleteMapping("/{idCita}")
    public ResponseEntity<Void> cancelarCita(@PathVariable Long idCita) {
        citasService.cancelarCita(idCita);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idCita}")
    public ResponseEntity<CitasModel> editarCita(@PathVariable Long idCita, @RequestBody CitasModel nuevaCita) {
        CitasModel citaEditada = citasService.editarCita(idCita, nuevaCita);
        return ResponseEntity.ok(citaEditada);
    }

    @GetMapping("/{idCita}")
    public ResponseEntity<CitasModel> getCitaById(@PathVariable Long idCita) {
        CitasModel cita = citasService.findCitaById(idCita);
        if (cita != null) {
            return ResponseEntity.ok(cita);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}