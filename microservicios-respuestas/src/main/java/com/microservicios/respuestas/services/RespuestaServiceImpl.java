package com.microservicios.respuestas.services;

import com.microservicios.respuestas.entity.Respuesta;
import com.microservicios.respuestas.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    @Autowired
    private RespuestaRepository repository;

    @Override
    @Transactional
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
        return repository.saveAll(respuestas);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Respuesta> findRespByAlumByExam(Long alumnoId, Long examenId) {
        return repository.findRespByAlumByExam(alumnoId, examenId);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
        return repository.findExamenesIdsConRespuestasByAlumno(alumnoId);
    }


}
