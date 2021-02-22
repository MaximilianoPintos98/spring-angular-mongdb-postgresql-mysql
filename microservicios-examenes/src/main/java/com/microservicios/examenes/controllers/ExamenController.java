package com.microservicios.examenes.controllers;

import com.commons.examenes.entity.Examen;
import com.microservicios.commons.controllers.CommonsController;
import com.microservicios.examenes.services.ExamenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ExamenController extends CommonsController<Examen, ExamenService> {

    @GetMapping("/respondidos-por-preguntas")
    public ResponseEntity<?> obtenerExamenesIdsPorPreguntasIdRespondidas(@RequestParam List<Long> preguntasIds){
        return ResponseEntity.ok().body(service.findExamenesIdsConRespuestasByPreguntasIds(preguntasIds));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id){

        if (result.hasErrors()) {
            return this.validar(result);
        }

        Optional<Examen> optional = service.findById(id);

        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Examen examenDb = optional.get();
        examenDb.setNombre(examen.getNombre());

        examenDb.getPreguntas()
                .stream()
                .filter(pdb -> !examen.getPreguntas().contains(pdb))
                .forEach(examenDb::removePreguntas);

        examenDb.setPreguntas(examen.getPreguntas());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
    }

    @GetMapping("/filtrar/{term}")
    public ResponseEntity<?> filtrar(@PathVariable String term){
        return ResponseEntity.ok(service.findByNombre(term));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsignaturas(){
        return ResponseEntity.ok(service.findAllAsignaturas());
    }
}
