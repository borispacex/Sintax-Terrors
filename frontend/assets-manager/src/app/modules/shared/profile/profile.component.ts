import { Component, Input, OnInit } from '@angular/core';
import { UserInterface } from './interfaces/user.interface';
import { AuthService } from '../../../services/auth.service';
import { Observable, of } from 'rxjs';
import { UserFacade } from './facades/user.facade';

@Component({
  selector: 'am-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  @Input() size: 'lg' | 'md' | 'sm' = 'lg';
  @Input() detail: boolean = false;

  public user$: Observable<UserInterface | null> = of(null);
  public loading$: Observable<boolean> = of(false);

  public showUserMenu: boolean;

  constructor(
    private _userFacade: UserFacade,
    private _authService: AuthService
  ) {
    this.showUserMenu = false;
  }

  public ngOnInit(): void {
    this._initialize();
  }

  public toggleUserMenu(): void {
    this.showUserMenu = !this.showUserMenu;
  }

  public logout(): void {
    this._authService.logout();
  }

  private _initialize(): void {
    this._userFacade.loadUser();
    this.loading$ = this._userFacade.getIsLoading$();
    this.user$ = this._userFacade.getUser$();
  }
}
