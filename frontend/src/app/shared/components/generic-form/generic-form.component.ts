import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';

export interface GenericFormField {
  key: string;
  label: string;
  type: 'text' | 'number' | 'select' | 'date';
  placeholder?: string;
  error?: string;
  options?: Array<{ value: any; label: string }>;
}

export interface GenericFormConfig {
  fields: GenericFormField[];
}

@Component({
  selector: 'app-generic-form',
  templateUrl: './generic-form.component.html',
  styleUrls: ['./generic-form.component.scss']
})
export class GenericFormComponent {
  @Input() config!: GenericFormConfig;
  @Input() form!: FormGroup;
  @Output() submit = new EventEmitter<void>();
  @Output() cancel = new EventEmitter<void>();

  onSubmit() {
    if (this.form.valid) {
      this.submit.emit();
    } else {
      this.form.markAllAsTouched();
    }
  }

  onCancel() {
    this.cancel.emit();
  }
}
