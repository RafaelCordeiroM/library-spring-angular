import { Component, inject, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CRUD_CONFIG, CrudConfig } from '../../../core/models/crud-config.interface';
import { MatProgressSpinner, MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatIcon, MatIconModule } from "@angular/material/icon";
import { SearchBarComponent } from "../search-bar/search-bar.component";
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-generic-list',
  templateUrl: './generic-list.component.html',
  styleUrls: ['./generic-list.component.scss'],
  imports: [
      MatProgressSpinner,
      MatIcon, 
      SearchBarComponent,
      CommonModule, 
      MatTableModule,
      MatButtonModule,
      MatIconModule,
      MatProgressSpinnerModule
  ]
})
export class GenericListComponent implements OnInit {
  config = inject<CrudConfig>(CRUD_CONFIG);

  service:any = inject(this.config.service);

  private router = inject(Router);
  private toastr = inject(ToastrService);

  data: any[] = [];
  displayedColumns: string[] = [];
  loading = false;

  ngOnInit() {
    this.displayedColumns = [...this.config.columns.map(c => c.key), 'actions'];
    this.loadData();
  }

  loadData() {
    this.loading = true;
    this.service.getAll().subscribe({
      next: (data: any) => {
        this.data = data.content;
        this.loading = false;
      },
      error: () => {
        this.toastr.error('Failed to load data');
        this.loading = false;
      }
    });
  }

  onSearch(term: string) {
    if (!term.trim()) {
      this.loadData();
      return;
    }
    this.service.search(term).subscribe((data: any) => this.data = data.content);
  }

  newItem() {
    this.router.navigate([this.config.route, 'new']);
  }

  edit(item: any) {
    this.router.navigate([this.config.route, item.id]);
  }

  delete(item: any) {
    if (confirm(`Delete ${item[this.config.displayField]}?`)) {
      this.service.delete(item.id).subscribe(() => {
        this.data = this.data.filter(i => i.id !== item.id);
        this.toastr.success('Deleted successfully');
      });
    }
  }
}