package com.microservicios.cursos.clients;

import com.microservicios.commons.alumnos.entity.Alumno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservicio-usuarios")
public interface AlumnoFeignClient {

    @GetMapping("/alumnos-por-curso")
    public Iterable<Alumno> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);
}
