// generic-form.component.ts
import { Component, inject, Injector, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CRUD_CONFIG, CrudConfig } from '../../../core/models/crud-config.interface';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-generic-form',
  standalone: true,
  templateUrl: './generic-form.component.html',
  styleUrls: ['./generic-form.component.scss'],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule
  ]
})
export class GenericFormComponent implements OnInit {
  config = inject<CrudConfig>(CRUD_CONFIG);
  service: any = inject(this.config.service); 
  private subs = new Subscription();
  private fb = inject(FormBuilder);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private toastr = inject(ToastrService);
  private injector = inject(Injector);

  form!: FormGroup;
  isEdit = false;
  options: Record<string, { value: any; label: string }[]> = {};

  ngOnInit() {
    this.buildForm();
    this.loadSelectOptions();
    this.loadDataIfEdit();
  }

  ngOnDestroy() {
    this.subs.unsubscribe();
  }

  private buildForm() {
    const controls: any = {};
    this.config.formFields.forEach(f => {
      const validators = f.required ? [Validators.required] : [];
      controls[f.key] = ['', validators];
    });
    this.form = this.fb.group(controls);
  }


private loadSelectOptions() {
  this.config.formFields
    .filter(f => f.type === 'select' && f.optionsService)
    .forEach(field => {
      try {
        const service = this.injector.get(field.optionsService!);

        service.getAll().subscribe({
          next: (data: any[]) => {
            this.options[field.key] = data.map(item => ({
              value: item.id,
              label: item[field.optionLabel || 'name']
            }));
          },
          error: (err:any) => {
            console.error(`Erro ao carregar ${field.label}:`, err);
            this.toastr.error(`Failed to load ${field.label} options`);
          }
        });
      } catch (error) {
        console.error(`Service not found:`, field.optionsService);
        this.toastr.error(`Service for ${field.label} not available`);
      }
    });
}

private loadDataIfEdit() {
  const id = this.route.snapshot.paramMap.get('id');
  if (!id) return;

  this.isEdit = true;
  this.service.getById(+id).subscribe({
    next: (data: any) => {
      const patchData: any = { ...data };

      // Converte autores para array de IDs
      if (data.authors && Array.isArray(data.authors)) {
        patchData.authors = data.authors.map((a: any) => a.id);
      }

      // Mesmo para category se for objeto
      if (data.category && typeof data.category === 'object') {
        patchData.category = data.category.id;
      }

      this.form.patchValue(patchData);
    },
    error: () => this.toastr.error('Failed to load data')
  });
}

private transformPayload(value: any): any {
  const payload = { ...value };

  this.config.formFields.forEach(field => {
    if (field.type === 'select' && value[field.key] !== undefined) {
      if (field.multiple) {
        // authors: [1, 2] → [{ id: 1 }, { id: 2 }]
        payload[field.key] = (value[field.key] as any[]).map(id => ({ id }));
      } else {
        // category: 1 → { id: 1 }
        payload[field.key] = { id: value[field.key] };
      }
    }
  });

  return payload;
}

save() {
  if (this.form.invalid) return;

  const rawValue = this.form.value;
  const payload = this.transformPayload(rawValue);

  const id = this.route.snapshot.paramMap.get('id');
  const obs = this.isEdit
    ? this.service.update(+id!, payload)
    : this.service.create(payload);

  obs.subscribe({
    next: () => {
      this.toastr.success(`${this.config.title} saved successfully`);
      this.router.navigate([this.config.route]);
    },
    error: (err:any) => {
      console.error('Save error:', err);
      this.toastr.error('Save failed');
    }
  });
}

  cancel() {
    this.router.navigate([this.config.route]);
  }
}