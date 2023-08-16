package com.reservas.controller;

import com.reservas.models.Comercio;
import com.reservas.models.Servicio;
import com.reservas.models.Turno;
import com.reservas.models.dto.TurnoRequest;
import com.reservas.service.AgendamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamiento")
public class AgendamientoController {
    @Autowired
    private AgendamientoService agendamientoService;

    @PostMapping("/generarTurnos")
    public ResponseEntity<List<Turno>> generarTurnos(@RequestBody TurnoRequest turnoRequest) {
        List<Turno> turnos = agendamientoService.generarTurnos(turnoRequest);
        return new ResponseEntity<>(turnos, HttpStatus.OK);
    }

    @GetMapping("/turnos")
    public ResponseEntity<List<Turno>> listTurnos() {
        List<Turno> turnos = agendamientoService.listTurnos();
        return new ResponseEntity<>(turnos, HttpStatus.OK);
    }

    @GetMapping("/comercios")
    public ResponseEntity<List<Comercio>> listComercios() {
        List<Comercio> comercios = agendamientoService.listComercios();
        return new ResponseEntity<>(comercios, HttpStatus.OK);
    }

    @GetMapping("/servicios/{idComercio}")
    public ResponseEntity<List<Servicio>> listServicios(@PathVariable Long idComercio) {
        List<Servicio> servicios = agendamientoService.listServiciosByComercio(idComercio);
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

}
