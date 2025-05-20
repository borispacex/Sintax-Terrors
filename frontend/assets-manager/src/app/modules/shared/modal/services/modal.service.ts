import { Injectable, TemplateRef } from '@angular/core';
import { ModalOptions } from '../interfaces/modal-options.interface';

@Injectable({
  providedIn: 'root',
})
export class ModalService {
  private _modalRef: TemplateRef<any> | null = null;
  private _showModal: boolean = false;
  private _modalOptions: ModalOptions = {
    size: 'md',
    scrollable: false,
    tittle: '',
  };

  constructor() {}

  open(
    modalRef: TemplateRef<any>,
    options: ModalOptions = this._modalOptions
  ): void {
    this._showModal = true;
    this._modalRef = modalRef;
    this._modalOptions = { ...this._modalOptions, ...options };
  }

  close(): void {
    this._showModal = false;
    setTimeout(() => {
      this._modalRef = null;
    }, 300);
  }

  getModalRef(): TemplateRef<any> | null {
    return this._modalRef;
  }

  getOptions(): ModalOptions {
    return this._modalOptions;
  }

  getShowModal(): boolean {
    return this._showModal;
  }
}
