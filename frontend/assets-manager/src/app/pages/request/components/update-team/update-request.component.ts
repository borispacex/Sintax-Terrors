import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';

import { FormBuilder, FormGroup } from '@angular/forms';
import { ModalService } from '../../../../modules/shared/modal/services/modal.service';
import { DropdownItem } from '../../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';
import {
  initialActiveRequest,
  initialRequestStatus,
  initialRequestType,
  initialUpdateRequestForm,
} from '../../helpers/initial-request-variables.helper';
import { RequestFacade } from '../../facades/request.facade';
import {
  Request,
  UpdateRequestRequest,
} from '../../interfaces/request.interface';

@Component({
  selector: 'am-update-request',
  templateUrl: './update-request.component.html',
  styleUrls: ['./update-request.component.scss'],
})
export class UpdateRequestComponent implements OnChanges {
  @Input() activeRequest: Request = initialActiveRequest;

  public requestForm: FormGroup = initialUpdateRequestForm;
  public requestStatusItems: DropdownItem[] = initialRequestStatus;
  public requestTypeItems: DropdownItem[] = initialRequestType;

  constructor(
    private readonly _modalService: ModalService,
    private readonly _requestFacade: RequestFacade,
    private readonly _fb: FormBuilder
  ) {}

  ngOnChanges(changes: SimpleChanges) {
    this.requestForm.patchValue({
      id: this.activeRequest.id,
      title: this.activeRequest.title,
      description: this.activeRequest.description,
      status: this.activeRequest.status,
      type: this.activeRequest.type,
    });
  }

  public closeModal(): void {
    this._modalService.close();
  }

  public updateRequest(): void {
    // TODO: Allow to the user select the asset ids to return or change
    const request: UpdateRequestRequest = this.requestForm.value;
    this._requestFacade.updateRequest(request);
  }
}
