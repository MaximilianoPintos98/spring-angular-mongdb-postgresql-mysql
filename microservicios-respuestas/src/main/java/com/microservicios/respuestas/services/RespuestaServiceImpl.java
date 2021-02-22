package com.microservicios.respuestas.services;

import com.commons.examenes.entity.Examen;
import com.commons.examenes.entity.Pregunta;
import com.microservicios.respuestas.clients.ExamenFeignClient;
import com.microservicios.respuestas.entity.Respuesta;
import com.microservicios.respuestas.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private ExamenFeignClient examenClient;

    @Override
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
        return repository.saveAll(respuestas);
    }

    @Override
    public Iterable<Respuesta> findRespByAlumByExam(Long alumnoId, Long examenId) {
        Examen examen = examenClient.obtenerExamenPorId(examenId);
        List<Pregunta> preguntas = examen.getPreguntas();
        List<Long> preguntaIds = preguntas.stream().map(p -> p.getId()).collect(Collectors.toList());
        List<Respuesta> respuestas = (List<Respuesta>) repository.findRespByAlumByPreguntaIds(alumnoId, preguntaIds);
        respuestas = respuestas.stream().map(r ->{
            preguntas.forEach(p ->{
                if(p.getId() == r.getPreguntaId()) {
                    r.setPregunta(p);
                }
            });
            return r;
        }).collect(Collectors.toList());

        return respuestas;
    }

    @Override
    public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
        List<Respuesta> respuestasAlumno = (List<Respuesta>) repository.findByAlumnoId(alumnoId);
        List<Long> examenIds = Collections.emptyList();
        if (respuestasAlumno.size() > 0) {
            List<Long> preguntaIds = respuestasAlumno.stream().map(respuesta -> respuesta.getPreguntaId()).collect(Collectors.toList());
            examenIds = examenClient.obtenerExamenesIdsPorPreguntasIdRespondidas(preguntaIds);
        }
        return examenIds;
    }

    @Override
    public Iterable<Respuesta> findByAlumnoId(Long alumnoId) {
        return repository.findByAlumnoId(alumnoId);
    }


}
