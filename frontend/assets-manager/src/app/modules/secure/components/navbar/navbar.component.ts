import { Component, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import {
  ScreenSize,
  ScreenSizeService,
} from '../../../../services/screen-size.service';

@Component({
  selector: 'am-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
})
export class NavbarComponent {
  @Output() toggleSidebarEvent = new EventEmitter<void>();

  public screenSize$: Observable<ScreenSize> =
    this._screenSizeService.screenSize$;

  constructor(private _screenSizeService: ScreenSizeService) {}

  public toggleSidebar() {
    this.toggleSidebarEvent.emit();
  }
}
