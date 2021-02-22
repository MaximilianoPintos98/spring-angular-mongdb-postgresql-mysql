package com.microservicios.examenes.services;

import com.commons.examenes.entity.Asignatura;
import com.commons.examenes.entity.Examen;
import com.microservicios.commons.services.CommonsServiceImpl;
import com.microservicios.examenes.repository.AsignaturaRepository;
import com.microservicios.examenes.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamenServiceImpl extends CommonsServiceImpl<Examen, ExamenRepository> implements ExamenService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String term) {
        return repository.findByNombre(term);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Asignatura> findAllAsignaturas() {
        return asignaturaRepository.findAll();
    }


}
