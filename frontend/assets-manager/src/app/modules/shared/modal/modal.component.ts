import { Component, TemplateRef } from '@angular/core';
import { ModalService } from './services/modal.service';
import { ModalOptions } from './interfaces/modal-options.interface';

@Component({
  selector: 'am-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss'],
})
export class ModalComponent {
  constructor(private _modalService: ModalService) {}

  public hideModal(): void {
    this._modalService.close();
  }

  get content(): TemplateRef<any> | null {
    return this._modalService.getModalRef();
  }

  get options(): ModalOptions {
    return this._modalService.getOptions();
  }

  get show(): boolean {
    return this._modalService.getShowModal();
  }
}
