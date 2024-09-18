package com.kosmos.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@Entity
@Table(name = "Doctores")
public class DoctoresModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoctor;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    @OneToMany(mappedBy = "doctoresModel")
    private List<CitasModel> citas;

}
