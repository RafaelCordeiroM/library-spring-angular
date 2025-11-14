import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';

import { SidebarComponent } from "./sidebar/sidebar.component";

@NgModule({
    declarations: [SidebarComponent],
    imports: [
        CommonModule,
        RouterModule,
        MatSidenavModule,
        MatListModule,
        MatIconModule
    ],
    exports: []
})
export class LayoutModule{}