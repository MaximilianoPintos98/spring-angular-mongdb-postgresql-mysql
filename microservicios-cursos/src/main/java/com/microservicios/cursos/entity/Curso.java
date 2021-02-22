package com.microservicios.cursos.entity;

import com.commons.examenes.entity.Examen;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservicios.commons.alumnos.entity.Alumno;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @Column(name="create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @JsonIgnoreProperties(value= {"curso"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CursoAlumno> cursoAlumnos;

    // @OneToMany(fetch = FetchType.LAZY)
    @Transient
    private List<Alumno> alumnos;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Examen> examenes;


    // GETTERS AND SETTERS

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Curso() {
        this.alumnos = new ArrayList<>();
        this.examenes = new ArrayList<>();
        this.cursoAlumnos = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void addAlumno(Alumno alumno) {
        this.alumnos.add(alumno);
    }

    public void removeAlumno(Alumno alumno) {
        this.alumnos.remove(alumno);
    }

    public List<Examen> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<Examen> examenes) {
        this.examenes = examenes;
    }

    public void addExamen(Examen examen) {
        this.examenes.add(examen);
    }

    public void removeExamen(Examen examen) {
        this.examenes.remove(examen);
    }

    public List<CursoAlumno> getCursoAlumnos() {
        return cursoAlumnos;
    }

    public void setCursoAlumnos(List<CursoAlumno> cursoAlumnos) {
        this.cursoAlumnos = cursoAlumnos;
    }

    public void addCursoAlumno(CursoAlumno cursoAlumnos) {
        this.cursoAlumnos.add(cursoAlumnos);
    }

    public void removeCursoAlumno(CursoAlumno cursoAlumnos) {
        this.cursoAlumnos.remove(cursoAlumnos);
    }


}
