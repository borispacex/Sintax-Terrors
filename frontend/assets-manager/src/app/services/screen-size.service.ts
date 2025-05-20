import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

export type ScreenSize = 'xs' | 'md' | 'lg';

@Injectable({
  providedIn: 'root',
})
export class ScreenSizeService {
  private readonly breakpoints = {
    xs: 768,
    md: 1024,
    lg: 1025,
  };

  private screenSizeSubject = new BehaviorSubject<ScreenSize>(
    this.getScreenSize()
  );
  public screenSize$: Observable<ScreenSize> =
    this.screenSizeSubject.asObservable();

  constructor() {
    window.addEventListener('resize', () => {
      this.screenSizeSubject.next(this.getScreenSize());
    });
  }

  private getScreenSize(): ScreenSize {
    const width = window.innerWidth;
    if (width < this.breakpoints.xs) {
      return 'xs';
    } else if (width < this.breakpoints.md) {
      return 'md';
    } else {
      return 'lg';
    }
  }
}
