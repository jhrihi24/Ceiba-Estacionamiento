import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { Servicios } from '../estructura-estacionamiento';

import { EstacionamientoService } from '../estacionamiento.service';
import { ConfirmationDialogService } from '../confirmation-dialog/confirmation-dialog.service';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.css']
})
export class TablaComponent implements OnInit {

    placaBuscar: string;
    servicio: Servicios;

    @Input() serviciosList: Servicios[];
    @Output() showMensajeSuccess = new EventEmitter();
    @Output() escribirMensajeSuccess = new EventEmitter();
    @Output() showMensajeDanger = new EventEmitter();
    @Output() escribirMensajeDanger = new EventEmitter();

    constructor(private estacionamientoService: EstacionamientoService,
        private confirmationDialogService: ConfirmationDialogService) { }

    ngOnInit() {
        this.placaBuscar = '';
        this.getServicios();
    }

    getServicios(): void {
        this.estacionamientoService.getServicios(this.placaBuscar).subscribe(respuesta =>{
            if (respuesta.success) {
                this.serviciosList = respuesta.data;
            } else {
                this.serviciosList = [];
            }
        });
    }

    darSalidaVehiculo(confirmed: boolean): void {
        if(confirmed){
            this.mostrarMensajeDanger('', false);
            this.mostrarMensajeSuccess('', false);
            this.estacionamientoService.darSalidaVehiculo(this.servicio.id).subscribe( respuesta =>{
                if(respuesta.success) {
                    this.mostrarMensajeSuccess(respuesta.mensaje, true);
                    this.deleteServicio();
                } else {
                    this.mostrarMensajeDanger(respuesta.mensaje, true);
                }
            });
        }
    }

    deleteServicio(): void {
        this.serviciosList = this.serviciosList.filter(h => h !== this.servicio);
        this.servicio = null;
    }

    mostrarMensajeSuccess(mensaje: string, mostrar: boolean): void {
      this.escribirMensajeSuccess.emit(mensaje);
      this.showMensajeSuccess.emit(mostrar);
    }

    mostrarMensajeDanger(mensaje: string, mostrar: boolean): void {
      this.escribirMensajeDanger.emit(mensaje);
      this.showMensajeDanger.emit(mostrar);
    }

    public openConfirmationDialog(servicioSeleccionado: Servicios) {
        this.servicio = servicioSeleccionado;
        this.confirmationDialogService.confirm('Confirmar', 'Realmente quiere dar de salida al veh\u00EDculo con placa ' + this.servicio.placa + '?')
        .then((confirmed) => this.darSalidaVehiculo(confirmed))
        .catch(() => console.log('Error'));
    }

}
