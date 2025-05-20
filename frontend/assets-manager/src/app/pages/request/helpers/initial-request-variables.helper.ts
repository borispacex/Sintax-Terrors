import { Request } from '../interfaces/request.interface';
import { initialActiveEmployee } from '../../employee/helpers/initial-employee-variables.helper';
import { RequestStatus } from '../enums/status.enum';
import { RequestType } from '../enums/request-type.enum';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DropdownItem } from '../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';

export const initialRequestTableData = {
  items: [],
  totalItems: 0,
  currentPage: 1,
  pageSize: 10,
  totalPages: 1,
};

export const initialCreateRequestForm: FormGroup = new FormGroup({
  title: new FormControl('', Validators.required),
  description: new FormControl(''),
  type: new FormControl(undefined, Validators.required),
  status: new FormControl(undefined, Validators.required),
});

export const initialUpdateRequestForm: FormGroup = new FormGroup({
  id: new FormControl(0, Validators.required),
  title: new FormControl('', Validators.required),
  description: new FormControl(''),
  type: new FormControl(undefined, Validators.required),
  status: new FormControl(undefined, Validators.required),
});

export const initialRequestStatus: DropdownItem[] = [
  { label: 'Active', value: RequestStatus.ACTIVE },
  { label: 'Inactive', value: RequestStatus.INACTIVE },
];

export const initialRequestType: DropdownItem[] = [
  { label: 'Assignment', value: RequestType.ASSIGNMENT },
  { label: 'Change', value: RequestType.CHANGE },
  { label: 'Return', value: RequestType.RETURN },
];

export const initialActiveRequest: Request = {
  id: 0,
  title: '',
  description: '',
  type: RequestType.ASSIGNMENT,
  status: RequestStatus.ACTIVE,
  employee: initialActiveEmployee,
  createdAt: '',
};
