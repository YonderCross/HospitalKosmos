package com.kosmos.hospital.service;

import com.kosmos.hospital.model.ConsultoriosModel;
import com.kosmos.hospital.repository.ConsultoriosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultoriosService {

    @Autowired
    private ConsultoriosRepository consultoriosRepository;

    public List<ConsultoriosModel> obtenerTodos() {
        return consultoriosRepository.findAll();
    }

    public ConsultoriosModel obtenerPorId(Long idConsultorio) {
        return consultoriosRepository.findById(idConsultorio)
                .orElseThrow(() -> new RuntimeException("Consultorio no encontrado"));
    }

    public ConsultoriosModel crearConsultorio(ConsultoriosModel consultorio) {
        return consultoriosRepository.save(consultorio);
    }

    public ConsultoriosModel actualizarConsultorio(Long idConsultorio, ConsultoriosModel consultorioActualizado) {
        ConsultoriosModel consultorioExistente = obtenerPorId(idConsultorio);
        consultorioExistente.setNumeroConsultorio(consultorioActualizado.getNumeroConsultorio());
        consultorioExistente.setPiso(consultorioActualizado.getPiso());
        return consultoriosRepository.save(consultorioExistente);
    }

    public void eliminarConsultorio(Long idConsultorio) {
        consultoriosRepository.deleteById(idConsultorio);
    }
}