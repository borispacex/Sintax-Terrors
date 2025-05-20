import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'am-secure-root',
  templateUrl: './secure.component.html',
  styleUrls: ['./secure.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SecureComponent {
  public isSidebarCollapsed = false;
  public isMobile = false;
  public showMobileSidebar = false;

  constructor() {
    this._checkScreenWidth();
    window.addEventListener('resize', () => this._checkScreenWidth());
  }

  private _checkScreenWidth() {
    this.isMobile = window.innerWidth <= 768;
    if (!this.isMobile) {
      this.showMobileSidebar = false;
    }
  }

  public toggleSidebar() {
    if (this.isMobile) {
      this.showMobileSidebar = !this.showMobileSidebar;
    } else {
      this.isSidebarCollapsed = !this.isSidebarCollapsed;
    }
  }
}
