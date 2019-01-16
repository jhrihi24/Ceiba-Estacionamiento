import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { RegistrarVehiculoDTO } from './estructura-estacionamiento';
import { Respuesta } from './estructura-respuesta';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
}

@Injectable({
  providedIn: 'root'
})
export class EstacionamientoService {

    private estacionamientoUrl = 'http://localhost:8080/api/estacionamiento';

    constructor(private http: HttpClient) { }

    getServicios (placa: string): Observable<Respuesta> {
        const url = placa.trim().length > 0 ? `${this.estacionamientoUrl}` + '?placa=' + placa : `${this.estacionamientoUrl}`;
        return this.http.get<Respuesta>(url).pipe(
            catchError(this.handleError<Respuesta>('getServicio'))
        );
    }

    addVehiculo (registroVehiculo: RegistrarVehiculoDTO): Observable<Respuesta> {
        return this.http.post<Respuesta>(this.estacionamientoUrl, registroVehiculo, httpOptions).pipe(
            catchError(this.handleError<Respuesta>('addVehiculo'))
        );
    }

    darSalidaVehiculo (id: number): Observable<Respuesta> {
        return this.http.put<Respuesta>(this.estacionamientoUrl, {idServicio: id}, httpOptions).pipe(
            catchError(this.handleError<Respuesta>('addVehiculo'))
        );
    }

    private handleError<T> (operation = 'operation', result?: T) {
      return (error: any): Observable<T> => {
        console.error(error); // log to console instead
        return of(result as T);
      };
   }
}
