import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Team } from '../interfaces/team.interface';
import { DropdownItem } from '../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';

export const initialCreateTeamForm: FormGroup = new FormGroup({
  name: new FormControl('', Validators.required),
  description: new FormControl(''),
  isActive: new FormControl(undefined, Validators.required),
  projectManagerId: new FormControl(undefined, Validators.required),
});

export const initialUpdateTeamForm: FormGroup = new FormGroup({
  id: new FormControl(0, Validators.required),
  name: new FormControl('', Validators.required),
  description: new FormControl(''),
  isActive: new FormControl(undefined, Validators.required),
  projectManagerId: new FormControl(undefined, Validators.required),
});

export const initialActiveTeam: Team = {
  id: 0,
  name: '',
  description: '',
  isActive: false,
};

export const initialIsActiveTeam: DropdownItem[] = [
  { label: 'Active', value: true },
  { label: 'Inactive', value: false },
];

export const initialTeamManager: DropdownItem = {
  label: 'Select an employee',
  value: 0,
};
