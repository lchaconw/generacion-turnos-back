CREATE PROCEDURE GenerarTurnos(
    p_fecha_inicio DATE,
    p_fecha_fin DATE,
    p_id_servicio INT,
    OUT p_mensaje VARCHAR(255)
)
BEGIN
    DECLARE v_hora_apertura TIME;
    DECLARE v_hora_cierre TIME;
    DECLARE v_duracion INT;
    DECLARE v_fecha_actual DATE;
    DECLARE v_hora_actual TIME;
    DECLARE v_hora_siguiente TIME;
    DECLARE v_existen_turnos INT;

    -- Consulta de la información del servicio
    SELECT hora_apertura, hora_cierre, duracion
    INTO v_hora_apertura, v_hora_cierre, v_duracion
    FROM Servicios
    WHERE id_servicio = p_id_servicio;
    
    -- Consulta si ya existen turnos para el servicio en esa fecha
    SELECT COUNT(*) INTO v_existen_turnos 
	FROM turnos 
	WHERE id_servicio = p_id_servicio AND fecha_turno BETWEEN p_fecha_inicio AND p_fecha_fin;

    SET v_fecha_actual = p_fecha_inicio;

    -- Si no existen turnos para el servicio en esa fecha, genera los turnos iterando sobre cada fecha
	IF v_existen_turnos = 0 THEN
		WHILE v_fecha_actual <= p_fecha_fin DO
			SET v_hora_actual = v_hora_apertura;

			-- Generamos turnos para la fecha actual
			WHILE TIME_TO_SEC(v_hora_actual) + (v_duracion * 60) <= TIME_TO_SEC(v_hora_cierre) DO
				SET v_hora_siguiente = ADDTIME(v_hora_actual, SEC_TO_TIME(v_duracion * 60));
				
				INSERT INTO Turnos(id_servicio, fecha_turno, hora_inicio, hora_fin, estado)
				VALUES(p_id_servicio, v_fecha_actual, v_hora_actual, v_hora_siguiente, 'Disponible');

				SET v_hora_actual = v_hora_siguiente;
				SET v_hora_actual = v_hora_siguiente;
			END WHILE;

			SET v_fecha_actual = DATE_ADD(v_fecha_actual, INTERVAL 1 DAY);
		END WHILE;
        SET p_mensaje = 'Turnos generados exitosamente.';
    ELSE
		SET p_mensaje = 'Ya existen turnos para el servicio en las fechas indicadas.';
	END IF;
END //
DELIMITER ;