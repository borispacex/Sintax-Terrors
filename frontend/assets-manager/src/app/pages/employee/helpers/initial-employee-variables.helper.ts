import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DropdownItem } from '../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';
import { Employee } from '../interfaces/employee.interface';
import { BoliviaCity } from '../../../interfaces/enums/bolivia-city.enum';
import { EmployeeStatus } from '../../../interfaces/enums/employee-status.enum';

export const initialCreateEmployeeForm: FormGroup = new FormGroup({
  image: new FormControl(''),
  firstName: new FormControl('', Validators.required),
  middleName: new FormControl(''),
  lastName: new FormControl('', Validators.required),
  secondLastName: new FormControl(''),
  personalEmail: new FormControl(''),
  workEmail: new FormControl('', [Validators.required, Validators.email]),
  city: new FormControl(BoliviaCity.COCHABAMBA, Validators.required),
  status: new FormControl(EmployeeStatus.ACTIVE, Validators.required),
  ci: new FormControl('', Validators.required),
  cellphoneNumber: new FormControl('', Validators.required),
  teamId: new FormControl(undefined, Validators.required),
  userId: new FormControl(undefined),
});

export const initialUpdateEmployeeForm: FormGroup = new FormGroup({
  image: new FormControl(''),
  id: new FormControl(0),
  firstName: new FormControl('', Validators.required),
  middleName: new FormControl(''),
  lastName: new FormControl('', Validators.required),
  secondLastName: new FormControl(''),
  personalEmail: new FormControl(''),
  workEmail: new FormControl('', [Validators.required, Validators.email]),
  city: new FormControl(BoliviaCity.COCHABAMBA, Validators.required),
  status: new FormControl(EmployeeStatus.ACTIVE, Validators.required),
  ci: new FormControl('', Validators.required),
  cellphoneNumber: new FormControl('', Validators.required),
  teamId: new FormControl(undefined, Validators.required),
  userId: new FormControl(undefined),
});

export const initialEmployeeTableData = {
  items: [],
  totalItems: 0,
  currentPage: 1,
  pageSize: 10,
  totalPages: 1,
};

export const initialActiveEmployee: Employee = {
  id: 0,
  ci: '',
  firstName: '',
  lastName: '',
  workEmail: '',
  country: '',
  city: BoliviaCity.LA_PAZ,
  cellphoneNumber: '',
  status: EmployeeStatus.ACTIVE,
};

export const initialEmployeeStatus: DropdownItem[] = [
  { label: 'ACTIVE', value: 'ACTIVE' },
  { label: 'INACTIVE', value: 'INACTIVE' },
  { label: 'VACATION', value: 'VACATION' },
];

export const initialEmployeeCity: DropdownItem[] = [
  { label: 'COCHABAMBA', value: BoliviaCity.COCHABAMBA },
  { label: 'LA PAZ', value: BoliviaCity.LA_PAZ },
  { label: 'SANTA CRUZ', value: BoliviaCity.SANTA_CRUZ },
  { label: 'BENI', value: BoliviaCity.BENI },
  { label: 'CHUQUISACA', value: BoliviaCity.CHUQUISACA },
  { label: 'TARIJA', value: BoliviaCity.TARIJA },
  { label: 'ORURO', value: BoliviaCity.ORURO },
  { label: 'PANDO', value: BoliviaCity.PANDO },
  { label: 'POTOSI', value: BoliviaCity.POTOSI },
];

export const initialEmployeeTeam: DropdownItem = {
  label: 'Select a team',
  value: 0,
};
