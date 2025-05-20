import { Component } from '@angular/core';

import { FormBuilder, FormGroup } from '@angular/forms';
import { ModalService } from '../../../../modules/shared/modal/services/modal.service';
import { DropdownItem } from '../../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';
import {
  initialCreateRequestForm,
  initialRequestStatus,
  initialRequestType,
} from '../../helpers/initial-request-variables.helper';
import { RequestFacade } from '../../facades/request.facade';
import { CreateRequestRequest } from '../../interfaces/request.interface';

@Component({
  selector: 'am-create-request',
  templateUrl: './create-request.component.html',
  styleUrls: ['./create-request.component.scss'],
})
export class CreateRequestComponent {
  public requestForm: FormGroup = initialCreateRequestForm;
  public requestStatusItems: DropdownItem[] = initialRequestStatus;
  public requestTypeItems: DropdownItem[] = initialRequestType;

  constructor(
    private readonly _modalService: ModalService,
    private readonly _requestFacade: RequestFacade,
    private readonly _fb: FormBuilder
  ) {}

  public closeModal(): void {
    this._modalService.close();
  }

  public createRequest(): void {
    // TODO: Allow to the user select the asset ids to return or change
    const request: CreateRequestRequest = this.requestForm.value;
    this._requestFacade.createRequest(request);
  }
}
