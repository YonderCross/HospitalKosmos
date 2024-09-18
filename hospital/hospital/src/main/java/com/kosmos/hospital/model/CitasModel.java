package com.kosmos.hospital.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter
@Entity
@Table(name = "Citas")
public class CitasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @ManyToOne
    @JoinColumn(name = "id_consultorio")
    private ConsultoriosModel consultoriosModel;

    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private DoctoresModel doctoresModel;

    private LocalDateTime horarioConsulta;
    private String nombrePaciente;

}
