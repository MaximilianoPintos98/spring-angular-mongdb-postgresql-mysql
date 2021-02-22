package com.microservicios.cursos.services;

import com.microservicios.commons.alumnos.entity.Alumno;
import com.microservicios.commons.services.CommonsServiceImpl;
import com.microservicios.cursos.clients.AlumnoFeignClient;
import com.microservicios.cursos.clients.RespuestaFeignClient;
import com.microservicios.cursos.entity.Curso;
import com.microservicios.cursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CursoServiceImpl extends CommonsServiceImpl<Curso, CursoRepository> implements CursoService {

    @Autowired
    private RespuestaFeignClient client;

    @Autowired
    private AlumnoFeignClient clientAlumno;

    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Long id) {
        return repository.findCursoByAlumnoId(id);
    }

    @Override
    public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
        return client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
    }

    @Override
    public Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids) {
        return clientAlumno.obtenerAlumnosPorCurso(ids);
    }

    @Override
    @Transactional
    public void eliminarCursoAlumnoPorId(Long id) {
        repository.eliminarCursoAlumnoPorId(id);
    }

}

