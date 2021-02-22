package com.microservicios.respuestas.services;

import com.microservicios.respuestas.entity.Respuesta;

public interface RespuestaService {

    Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);

    Iterable<Respuesta> findRespByAlumByExam(Long alumnoId, Long examenId);

    Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);

    Iterable<Respuesta> findByAlumnoId(Long alumnoId);

}
