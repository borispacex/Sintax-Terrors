import { BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { ToastModel } from '../interfaces/toast.model.interface';

@Injectable({
  providedIn: 'root',
})
export class ToastService {
  private _toastSubject: BehaviorSubject<ToastModel[]> = new BehaviorSubject<
    ToastModel[]
  >([]);

  private _toasts$: Observable<ToastModel[]> =
    this._toastSubject.asObservable();

  public show(toast: ToastModel): void {
    const currentToasts = this._toastSubject.getValue();
    this._toastSubject.next([...currentToasts, toast]);
    setTimeout(() => {
      this._removeToast(toast);
    }, 3500);
  }

  public getToasts(): Observable<ToastModel[]> {
    return this._toasts$;
  }

  private _removeToast(toast: ToastModel): void {
    const currentToasts = this._toastSubject.getValue();
    this._toastSubject.next(currentToasts.filter(t => t !== toast));
  }
}
