package com.microservicios.respuestas.repository;

import com.microservicios.respuestas.entity.Respuesta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RespuestaRepository extends MongoRepository<Respuesta, String> {

    @Query("{ 'alumnoId': ?0, 'preguntaId': { $in: ?1} }")
    Iterable<Respuesta> findRespByAlumByPreguntaIds(Long alumnoId,Iterable <Long> preguntaIds);

    @Query("{'alumnoId': ?0}")
    Iterable<Respuesta> findByAlumnoId(Long alumnoId);

    // @Query("select r from Respuesta r  join fetch r.pregunta p join fetch p.examen e where r.alumnoId=?1 and e.id=?2")
    // Iterable<Respuesta> findRespByAlumByExam(Long alumnoId, Long examenId);

    // @Query("select e.id from Respuesta r join r.pregunta p join p.examen e where r.alumnoId=?1 group by e.id")
    // Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);

}
