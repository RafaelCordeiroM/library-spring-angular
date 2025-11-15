import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { GenericListComponent } from './generic-list/generic-list.component';
import { GenericFormComponent } from './generic-form/generic-form.component';
import { SearchBarComponent } from './search-bar/search-bar.component';


@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    GenericListComponent,
    GenericFormComponent,
    SearchBarComponent
  ],
  exports: [
    GenericListComponent,
    GenericFormComponent,
    SearchBarComponent
  ],
})
export class GenericCrudModule { }