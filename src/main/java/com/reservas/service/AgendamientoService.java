package com.reservas.service;

import com.reservas.exceptions.CustomBadRequestException;
import com.reservas.models.Comercio;
import com.reservas.models.Servicio;
import com.reservas.models.Turno;
import com.reservas.models.dto.TurnoRequest;
import com.reservas.repository.ComercioRepository;
import com.reservas.repository.ServicioRepository;
import com.reservas.repository.TurnoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AgendamientoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private ComercioRepository comercioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Turno> generarTurnos(TurnoRequest turnoRequest) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GenerarTurnos");
        storedProcedure.registerStoredProcedureParameter("p_fecha_inicio", Date.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_fecha_fin", Date.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_id_servicio", Long.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("p_mensaje_salida", String.class, ParameterMode.OUT);

        storedProcedure.setParameter("p_fecha_inicio", turnoRequest.getFechaInicio());
        storedProcedure.setParameter("p_fecha_fin", turnoRequest.getFechaFin());
        storedProcedure.setParameter("p_id_servicio", turnoRequest.getIdServicio());

        storedProcedure.execute();
        String mensajeSalida = (String) storedProcedure.getOutputParameterValue("p_mensaje_salida");

        if (!mensajeSalida.equals("Turnos generados exitosamente.")) {
            throw new CustomBadRequestException(mensajeSalida);
        }

        return listTurnos();
    }

    public List<Turno> listTurnos() {
        return turnoRepository.findAll();
    }

    public List<Comercio> listComercios() {
        return comercioRepository.findAll();
    }

    public List<Servicio> listServicios() {
        return servicioRepository.findAll();
    }

    public List<Servicio> listServiciosByComercio(Long idComercio) {
        return servicioRepository.findByComercioId(idComercio);
    }
}
