import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { ToastContainerDirective } from 'ngx-toastr';


@Component({
  selector: 'app-root',
  imports: [SidebarComponent, RouterOutlet, ToastContainerDirective],
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'frontend';
}
