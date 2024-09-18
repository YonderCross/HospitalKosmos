package com.kosmos.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@Entity
@Table(name = "Consultorios")
public class ConsultoriosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsultorio;
    private Integer numeroConsultorio;
    private Integer piso;

    @OneToMany(mappedBy = "consultoriosModel")
    private List<CitasModel> citas;
}
