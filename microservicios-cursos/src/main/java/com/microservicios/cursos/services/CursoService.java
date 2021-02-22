package com.microservicios.cursos.services;

import com.microservicios.commons.alumnos.entity.Alumno;
import com.microservicios.commons.services.CommonsService;
import com.microservicios.cursos.entity.Curso;

public interface CursoService extends CommonsService<Curso> {

    Curso findCursoByAlumnoId(Long id);

    Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);

    Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids);

    void eliminarCursoAlumnoPorId(Long id);
}