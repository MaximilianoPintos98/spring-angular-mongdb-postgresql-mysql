package com.microservicios.examenes.repository;

import com.commons.examenes.entity.Asignatura;
import org.springframework.data.repository.CrudRepository;

public interface AsignaturaRepository extends CrudRepository<Asignatura, Long> {
}
