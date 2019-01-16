import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { FormEstacionamientoRoutingModule } from './routing.form-estacionamiento.module';
import { RegistroComponent } from './registro/registro.component';
import { TablaComponent } from './tabla/tabla.component';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { ConfirmationDialogService } from './confirmation-dialog/confirmation-dialog.service';

@NgModule({
  declarations: [RegistroComponent, TablaComponent, ConfirmationDialogComponent],
  imports: [
    CommonModule,
    FormEstacionamientoRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule.forRoot()
  ],
  providers: [ ConfirmationDialogService ],
  entryComponents: [ ConfirmationDialogComponent ]
})
export class FormEstacionamientoModule { }
