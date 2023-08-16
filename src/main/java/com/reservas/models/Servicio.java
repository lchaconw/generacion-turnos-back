package com.reservas.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_comercio")
    private Comercio comercio;
    @Column(name = "nom_servicio")
    private String nombre;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private int duracion;

}
