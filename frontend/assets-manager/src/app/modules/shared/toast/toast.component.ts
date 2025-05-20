import { ChangeDetectionStrategy, Component } from '@angular/core';
import { ToastModel } from './interfaces/toast.model.interface';
import { ToastService } from './services/toast.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'am-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ToastComponent {
  public toasts$: Observable<ToastModel[]> = this._toastService.getToasts();

  constructor(private _toastService: ToastService) {}
}
