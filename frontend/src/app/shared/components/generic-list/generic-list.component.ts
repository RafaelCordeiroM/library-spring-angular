// src/app/shared/components/generic-list/generic-list.component.ts
import { Component, Inject, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CRUD_CONFIG, CrudConfig } from '../../../core/models/crud-config.interface';

@Component({
  selector: 'app-generic-list',
  templateUrl: './generic-list.component.html',
  styleUrls: ['./generic-list.component.scss']
})
export class GenericListComponent implements OnInit {
  @Input() config!: CrudConfig;
  data: any[] = [];
  displayedColumns: string[] = [];
  loading = false;

  constructor(
    @Inject(CRUD_CONFIG) private crudConfig: CrudConfig,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.config = crudConfig;
  }

  ngOnInit() {
    this.displayedColumns = [...this.config.columns.map(c => c.key), 'actions'];
    this.load();
  }

  load() {
    this.loading = true;
    this.config.service.getAll().subscribe({
      next: (data: any[]) => {
        this.data = data;
        this.loading = false;
      },
      error: () => {
        this.toastr.error('Error loading data');
        this.loading = false;
      }
    });
  }

  onSearch(term: string) {
    if (!term.trim()) {
      this.load();
      return;
    }
    this.config.service.search(term).subscribe((data: any[]) => this.data = data);
  }

  newNavigation() {
    this.router.navigate([this.config.route, '']);
  }

  edit(item: any) {
    this.router.navigate([this.config.route, item.id]);
  }

  remove(item: any) {
    if (confirm(`remove ${item[this.config.displayField]}?`)) {
      this.config.service.delete(item.id).subscribe(() => {
        this.data = this.data.filter(i => i.id !== item.id);
        this.toastr.success('removed successfully');
      });
    }
  }
}