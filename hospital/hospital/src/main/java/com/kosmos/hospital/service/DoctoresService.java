package com.kosmos.hospital.service;
import com.kosmos.hospital.model.DoctoresModel;
import com.kosmos.hospital.repository.DoctoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctoresService {

    @Autowired
    private DoctoresRepository doctoresRepository;

    public List<DoctoresModel> obtenerTodos() {
        return doctoresRepository.findAll();
    }

    public DoctoresModel obtenerPorId(Long idDoctor) {
        return doctoresRepository.findById(idDoctor)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
    }

    public DoctoresModel crearDoctor(DoctoresModel doctor) {
        return doctoresRepository.save(doctor);
    }

    public DoctoresModel actualizarDoctor(Long idDoctor, DoctoresModel doctorActualizado) {
        DoctoresModel doctorExistente = obtenerPorId(idDoctor);
        doctorExistente.setNombre(doctorActualizado.getNombre());
        doctorExistente.setApellidoPaterno(doctorActualizado.getApellidoPaterno());
        doctorExistente.setApellidoMaterno(doctorActualizado.getApellidoMaterno());
        doctorExistente.setEspecialidad(doctorActualizado.getEspecialidad());
        return doctoresRepository.save(doctorExistente);
    }

    public void eliminarDoctor(Long idDoctor) {
        doctoresRepository.deleteById(idDoctor);
    }
}