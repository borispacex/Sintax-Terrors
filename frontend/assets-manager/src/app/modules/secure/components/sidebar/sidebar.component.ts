import { Component, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { UserInterface } from '../../../shared/profile/interfaces/user.interface';
import { UserFacade } from '../../../shared/profile/facades/user.facade';

@Component({
  selector: 'am-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],
})
export class SidebarComponent {
  @Input() isCollapsed = false;
  @Input() showMobileSidebar = false;
  public user$: Observable<UserInterface | null> = this._userFacade.getUser$();

  constructor(private _userFacade: UserFacade) {}
}
