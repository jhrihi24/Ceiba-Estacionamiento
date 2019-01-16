import { Component, OnInit } from '@angular/core';

import { RegistrarVehiculoDTO, Servicios } from '../estructura-estacionamiento';

import { EstacionamientoService } from '../estacionamiento.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

   registroVehiculo: RegistrarVehiculoDTO = {
       placa: '',
       cilindraje: 0
   };

   serviciosIngresados: Servicios[];

   showSuccess: boolean = false;
   mensajeSuccess: string;
   showDanger: boolean = false;
   mensajeDanger: string;

   constructor(private estacionamientoService: EstacionamientoService) { }

   ngOnInit() {
   }

   limpiarRegistroVehiculo(): void {
       this.registroVehiculo.placa = '';
       this.registroVehiculo.cilindraje = 0;
   }

   validarInfoVehiculo(): string {
     let strValidacion = '';
     if (this.registroVehiculo.placa.trim().length === 0) {
         strValidacion = 'Ingrese una placa';
     }

     return strValidacion;
   }

   registrarVehiculo(): void{
       let validacion= this.validarInfoVehiculo();
       if (validacion.trim().length === 0) {
            this.mostrarMensajeDanger('', false);
            this.mostrarMensajeSuccess('', false);
            this.estacionamientoService.addVehiculo(this.registroVehiculo).subscribe( respuesta =>{
                if(respuesta.success){
                    this.mostrarMensajeSuccess(respuesta.mensaje, true);
                    this.getServicios();
                    this.limpiarRegistroVehiculo();
                }else{
                    this.mostrarMensajeDanger(respuesta.mensaje, true);
                }
            });
       }else{
            this.mostrarMensajeDanger(validacion, true);
       }
    }

    getServicios(): void {
        this.estacionamientoService.getServicios('').subscribe(respuesta =>{
            if (respuesta.success) {
                this.serviciosIngresados = respuesta.data;
                console.log(this.serviciosIngresados);
            } else {
                this.serviciosIngresados = [];
            }
        });
    }

    mostrarMensajeSuccess(mensaje: string, mostrar: boolean): void{
      this.mensajeSuccess = mensaje;
      this.showSuccess = mostrar;
    }

    mostrarMensajeDanger(mensaje: string, mostrar: boolean): void{
      this.mensajeDanger = mensaje;
      this.showDanger = mostrar;
    }

}
