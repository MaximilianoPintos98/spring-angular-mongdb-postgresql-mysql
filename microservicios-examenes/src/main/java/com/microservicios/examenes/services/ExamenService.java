package com.microservicios.examenes.services;

import com.commons.examenes.entity.Asignatura;
import com.commons.examenes.entity.Examen;
import com.microservicios.commons.services.CommonsService;

import java.util.List;

public interface ExamenService extends CommonsService<Examen> {

    List<Examen> findByNombre(String term);

    Iterable<Asignatura> findAllAsignaturas();

    Iterable<Long>  findExamenesIdsConRespuestasByPreguntasIds(Iterable<Long> preguntasIds);
}
