package com.formacionbdi.microservicios.app.usuarios.services;

import com.microservicios.commons.alumnos.entity.Alumno;
import com.microservicios.commons.services.CommonsService;


import java.util.List;


public interface AlumnoService extends CommonsService<Alumno>{

    List<Alumno> findByNombreOrApellido(String term);

    Iterable<Alumno> findAllById(Iterable<Long> ids);

    void eliminarCursoAlumnoPorId(Long id);
}

