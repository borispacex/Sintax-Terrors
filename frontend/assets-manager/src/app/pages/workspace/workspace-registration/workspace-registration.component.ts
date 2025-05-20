import {
  AfterViewInit,
  ChangeDetectionStrategy,
  Component,
  Input,
} from '@angular/core';
import { DropdownItem } from '../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ItemValidator } from '../../../modules/shared/autocomplete-dropdown/validators/item.validator';
import {
  CreateWorkspaceRequest,
  UpdateWorkspaceRequest,
  Workspace,
} from '../interfaces/workspace.interface';
import { WorkspaceFacade } from '../facades/workspace.facade';
import { ModalService } from '../../../modules/shared/modal/services/modal.service';
import { initialWorkspaceCityItems } from '../helpers/initial-workspace-variables.helper';

@Component({
  selector: 'am-workspace-registration',
  templateUrl: './workspace-registration.component.html',
  styleUrls: ['./workspace-registration.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class WorkspaceRegistrationComponent implements AfterViewInit {
  @Input() workspace: Workspace | null = null;

  public readonly items: DropdownItem[] = initialWorkspaceCityItems;
  public workspaceForm: FormGroup;

  constructor(
    private _fb: FormBuilder,
    private _workspaceFacade: WorkspaceFacade,
    private _modalService: ModalService
  ) {
    this.workspaceForm = this._fb.group({
      city: ['', [Validators.required, ItemValidator(this.items, false)]],
      location: ['', [Validators.required, Validators.minLength(5)]],
    });
  }

  ngAfterViewInit(): void {
    if (this.workspace) {
      this.workspaceForm.setValue({
        city: this.workspace.city,
        location: this.workspace.location,
      });
    }
  }

  public createWorkspace(): void {
    const request: CreateWorkspaceRequest = this.workspaceForm.value;
    this._workspaceFacade.createWorkspace(request);
  }

  public updateWorkspace(): void {
    const request: CreateWorkspaceRequest = this.workspaceForm.value;
    this._workspaceFacade.updateWorkspace({
      id: this.workspace?.id,
      location: request.location,
      city: request.city,
    } as UpdateWorkspaceRequest);
  }

  public closeModal(): void {
    this._modalService.close();
  }
}
