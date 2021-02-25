import { Injectable } from '@angular/core';
import { Alumno } from '../models/alumno';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class ExamenService extends CommonService<Alumno>{

  protected baseEndPoint = 'http://localhost:8090/api/examenes';

}