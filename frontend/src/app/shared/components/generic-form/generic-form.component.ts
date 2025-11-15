import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CRUD_CONFIG, CrudConfig } from '../../../core/models/crud-config.interface';
import { MatSelect, MatFormField, MatLabel, MatOption, MatError, MatSelectModule } from "@angular/material/select";
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-generic-form',
  templateUrl: './generic-form.component.html',
  styleUrls: ['./generic-form.component.scss'],
  imports: [
    MatSelect,
    MatFormField, 
    MatLabel, 
    MatOption, 
    MatError,
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule
]
})
export class GenericFormComponent implements OnInit{
  form!: FormGroup;
  isEdit = false;
  options: any = {};

  constructor(
    @Inject(CRUD_CONFIG) public config: CrudConfig,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.buildForm();
    this.loadSelectOptions();

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.config.service.getById(+id).subscribe((data: any) => this.form.patchValue(data));
    }
  }

  buildForm() {
    const controls: any = {};
    this.config.formFields.forEach(f => {
      controls[f.key] = ['', f.required ? Validators.required : []];
    });
    this.form = this.fb.group(controls);
  }

  loadSelectOptions() {
    this.config.formFields
      .filter(f => f.type === 'select' && f.optionsService)
      .forEach(f => {
        f.optionsService.getAll().subscribe((data: any[]) => {
          this.options[f.key] = data.map(d => ({
            value: d.id,
            label: d[f.optionLabel!]
          }));
        });
      });
  }

  save() {
    if (this.form.invalid) return;

    const obs = this.isEdit
      ? this.config.service.update(this.route.snapshot.params['id'], this.form.value)
      : this.config.service.create(this.form.value);

    obs.subscribe({
      next: () => {
        this.toastr.success(`${this.config.title} saved`);
        this.router.navigate([this.config.route]);
      },
      error: () => this.toastr.error('Save failed')
    });
  }

  cancel() {
    this.router.navigate([this.config.route]);
  }
}