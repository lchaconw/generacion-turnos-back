package com.reservas.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comercios")
public class Comercio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comercio")
    private Long id;
    @Column(name = "nom_comercio")
    private String nombre;
    @Column(name = "aforo_maximo")
    private int aforoMaximo;

}