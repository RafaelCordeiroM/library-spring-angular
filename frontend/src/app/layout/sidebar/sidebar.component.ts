import { Component } from "@angular/core";
import { MatSidenavContainer, MatSidenav } from "@angular/material/sidenav";
import { MatNavList } from "@angular/material/list";
import { MatIcon } from "@angular/material/icon";

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.scss'],
    imports: [MatSidenavContainer, MatNavList, MatIcon, MatSidenav]
})
export class SidebarComponent {}