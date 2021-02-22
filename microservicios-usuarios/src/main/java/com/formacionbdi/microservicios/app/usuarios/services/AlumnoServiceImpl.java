package com.formacionbdi.microservicios.app.usuarios.services;

import com.formacionbdi.microservicios.app.usuarios.clients.CursoFeignClient;
import com.formacionbdi.microservicios.app.usuarios.models.repository.AlumnoRepository;
import com.microservicios.commons.alumnos.entity.Alumno;
import com.microservicios.commons.services.CommonsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AlumnoServiceImpl extends CommonsServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

    @Autowired
    private CursoFeignClient clientCurso;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findByNombreOrApellido(String term) {
        return repository.findByNombreOrApellido(term);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Alumno> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void eliminarCursoAlumnoPorId(Long id) {
        clientCurso.eliminarCursoAlumnoPorId(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        super.deleteById(id);
        this.eliminarCursoAlumnoPorId(id);
    }

}
