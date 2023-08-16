package com.reservas.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;
    private LocalDate fechaTurno;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;

}
