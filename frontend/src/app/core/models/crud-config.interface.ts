import { InjectionToken } from '@angular/core';

export interface ColumnDef {
  key: string;
  header: string;
  cell?: (item: any) => any;
}

export interface FormField {
  key: string;
  label: string;
  type: 'text' | 'number' | 'select';
  required?: boolean;
  optionsService?: any;
  optionLabel?: string;
  options?: { value: any; label: string }[];
}

export interface CrudConfig {
  title: string;
  newLabel: string;
  route: string;
  displayField: string;
  columns: ColumnDef[];
  formFields: FormField[];
  service: any;
}

export const CRUD_CONFIG = new InjectionToken<CrudConfig>('CRUD_CONFIG');