import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router, ParamMap, Data, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';
import { GenericListComponent } from '../../shared/components/generic-list/generic-list.component';
import { GenericFormComponent } from '../../shared/components/generic-form/generic-form.component';
import { BOOKS_CONFIG } from './config/books.config';
import { CRUD_CONFIG } from '../../core/models/crud-config.interface';
import { filter, startWith, switchMap, combineLatest, Subscription } from 'rxjs';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss'],
  standalone: true,
  imports: [CommonModule, GenericListComponent, GenericFormComponent],
  providers: [{ provide: CRUD_CONFIG, useValue: BOOKS_CONFIG }]
})
export class BooksComponent implements OnInit, OnDestroy {
  mode: 'list' | 'form' = 'list';
  bookId: string | null = null;

  private sub!: Subscription;

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {

    this.sub = this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      startWith(null),
      switchMap(() => {
        const child = this.route.firstChild;
        const data$ = child?.data ?? this.route.data;
        const params$ = child?.paramMap ?? this.route.paramMap;
        return combineLatest([data$, params$]);
      })
    ).subscribe(([data, params]) => {
      this.applyRoute(data, params);
    });
  }

  ngOnDestroy(): void {
    this.sub?.unsubscribe();
  }

  private applyRoute(data: Data, params: ParamMap): void {
    const mode = data['mode'] as 'list' | 'form';

    if (mode === 'list') {
      this.mode = 'list';
      this.bookId = null;
    } else {
      this.mode = 'form';
      this.bookId = data['isNew'] ? null : params.get('id');
    }
  }

  // Navegação
  onCreate(): void {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(id: string): void {
    this.router.navigate([id], { relativeTo: this.route });
  }

  onSaveOrCancel(): void {
    this.router.navigate(['../'], { relativeTo: this.route });
  }
}