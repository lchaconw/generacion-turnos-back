package com.reservas.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TurnoRequest {
    private Date fechaInicio;
    private Date fechaFin;
    private Long idServicio;
}
