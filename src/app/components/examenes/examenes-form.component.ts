import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Asignatura } from 'src/app/models/asignatura';
import { Examen } from 'src/app/models/examen';
import { Pregunta } from 'src/app/models/pregunta';
import { ExamenService } from 'src/app/services/examen.service';
import Swal from 'sweetalert2';
import { CommonFormComponent } from '../common-form.component';

@Component({
  selector: 'app-examenes-form',
  templateUrl: './examenes-form.component.html',
  styleUrls: ['./examenes-form.component.css']
})
export class ExamenesFormComponent extends CommonFormComponent<Examen, ExamenService> implements OnInit {

  asignaturasPadre: Asignatura[] = [];
  asignaturasHija: Asignatura[] = [];

  // errorPreguntas!: string;

  constructor(service: ExamenService,
    router: Router,
    route: ActivatedRoute) {
    super(service, router, route);
    this.titulo = 'Crear Examen';
    this.model = new Examen();
    this.nombreModel = Examen.name;
    this.redirect = '/examenes';
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const id: number = Number(params.get('id'));
      if (id) {
        this.service.ver(id).subscribe(m => {
          this.model = m;
          this.titulo = 'Editar ' + this.nombreModel;
          this.cargarHijos();
          /*this.service.findAllAsignatura().subscribe(asignaturas => {
            this.asignaturasHija = asignaturas
              .filter(a => a.padre && a.padre.id === this.model.asignaturaPadre.id);
          })*/
        });
      }
    });

    this.service.findAllAsignatura().subscribe(asignaturas => {
      this.asignaturasPadre = asignaturas.filter(a => !a.padre);
    })
  }

  public crear(): void {
    if (this.model.preguntas.length === 0) {
      Swal.fire('Error Preguntas', 'Examen debe tener Preguntas', 'error');
      return;
    }
    this.eliminarPreguntasVacias();
    super.crear();
    
  }

  public editar():void {
    if (this.model.preguntas.length === 0) {
      Swal.fire('Error Preguntas', 'Examen debe tener Preguntas', 'error');
      return;
    }
    this.eliminarPreguntasVacias();
    super.editar();
  }

  cargarHijos(): void {
    this.asignaturasHija = this.model.asignaturaPadre ? this.model.asignaturaPadre.hijos : [];
  }

  compararAsignatura(a1: Asignatura, a2: Asignatura): boolean {
    if (a1 === undefined && a2 === undefined) {
      return true;
    }
    return (a1 === null || a2 === null || a1 === undefined || a2 === undefined)
      ? false : a1.id === a2.id;
  }

  agregarPregunta(): void{
    this.model.preguntas.push(new Pregunta());
  }

  asignarTexto(pregunta: Pregunta, event: any):void {
    pregunta.texto = event.target.value as string;
  }

  eliminarPregunta(pregunta: { texto: string; }):void{
    this.model.preguntas = this.model.preguntas.filter(p => pregunta.texto !== p.texto);
  }

  eliminarPreguntasVacias(): void {
    this.model.preguntas = this.model.preguntas.filter(preg => preg.texto !=null && preg.texto.length > 0)
  }
}
