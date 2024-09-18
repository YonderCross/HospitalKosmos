package com.kosmos.hospital.controller;

import com.kosmos.hospital.model.DoctoresModel;
import com.kosmos.hospital.service.DoctoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
public class DoctoresController {

    @Autowired
    private DoctoresService doctoresService;

    @GetMapping
    public ResponseEntity<List<DoctoresModel>> obtenerTodos() {
        List<DoctoresModel> doctores = doctoresService.obtenerTodos();
        return ResponseEntity.ok(doctores);
    }

    @GetMapping("/{idDoctor}")
    public ResponseEntity<DoctoresModel> obtenerPorId(@PathVariable Long idDoctor) {
        DoctoresModel doctor = doctoresService.obtenerPorId(idDoctor);
        return ResponseEntity.ok(doctor);
    }

    @PostMapping
    public ResponseEntity<DoctoresModel> crearDoctor(@RequestBody DoctoresModel doctor) {
        DoctoresModel nuevoDoctor = doctoresService.crearDoctor(doctor);
        return ResponseEntity.ok(nuevoDoctor);
    }

    @PutMapping("/{idDoctor}")
    public ResponseEntity<DoctoresModel> actualizarDoctor(@PathVariable Long idDoctor,
                                                          @RequestBody DoctoresModel doctorActualizado) {
        DoctoresModel doctor = doctoresService.actualizarDoctor(idDoctor, doctorActualizado);
        return ResponseEntity.ok(doctor);
    }

    @DeleteMapping("/{idDoctor}")
    public ResponseEntity<Void> eliminarDoctor(@PathVariable Long idDoctor) {
        doctoresService.eliminarDoctor(idDoctor);
        return ResponseEntity.noContent().build();
    }
}
