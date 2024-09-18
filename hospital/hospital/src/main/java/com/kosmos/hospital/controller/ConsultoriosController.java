package com.kosmos.hospital.controller;
import com.kosmos.hospital.model.ConsultoriosModel;
import com.kosmos.hospital.service.ConsultoriosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultorios")
public class ConsultoriosController {

    @Autowired
    private ConsultoriosService consultoriosService;

    @GetMapping
    public ResponseEntity<List<ConsultoriosModel>> obtenerTodos() {
        List<ConsultoriosModel> consultorios = consultoriosService.obtenerTodos();
        return ResponseEntity.ok(consultorios);
    }

    @GetMapping("/{idConsultorio}")
    public ResponseEntity<ConsultoriosModel> obtenerPorId(@PathVariable Long idConsultorio) {
        ConsultoriosModel consultorio = consultoriosService.obtenerPorId(idConsultorio);
        return ResponseEntity.ok(consultorio);
    }

    @PostMapping
    public ResponseEntity<ConsultoriosModel> crearConsultorio(@RequestBody ConsultoriosModel consultorio) {
        ConsultoriosModel nuevoConsultorio = consultoriosService.crearConsultorio(consultorio);
        return ResponseEntity.ok(nuevoConsultorio);
    }

    @PutMapping("/{idConsultorio}")
    public ResponseEntity<ConsultoriosModel> actualizarConsultorio(@PathVariable Long idConsultorio,
                                                                   @RequestBody ConsultoriosModel consultorioActualizado) {
        ConsultoriosModel consultorio = consultoriosService.actualizarConsultorio(idConsultorio, consultorioActualizado);
        return ResponseEntity.ok(consultorio);
    }

    @DeleteMapping("/{idConsultorio}")
    public ResponseEntity<Void> eliminarConsultorio(@PathVariable Long idConsultorio) {
        consultoriosService.eliminarConsultorio(idConsultorio);
        return ResponseEntity.noContent().build();
    }
}