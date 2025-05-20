import { DropdownItem } from '../../../modules/shared/autocomplete-dropdown/interfaces/dropdown.model';
import { AssetStatus } from '../enums/asset-status.enum';
import { FormControl, FormGroup, Validators } from '@angular/forms';

export const initialAssetStatus: DropdownItem[] = [
  { label: 'AVAILABLE', value: AssetStatus.AVAILABLE },
  { label: 'ASIGNED', value: AssetStatus.ASIGNED },
  { label: 'UNDER_MAINTENANCE', value: AssetStatus.UNDER_MAINTENANCE },
  { label: 'RETIRED', value: AssetStatus.RETIRED },
  { label: 'OBSOLETE', value: AssetStatus.OBSOLETE },
  { label: 'LOST', value: AssetStatus.LOST },
  { label: 'STOLEN', value: AssetStatus.STOLEN },
];
export const initialAssetCategory: DropdownItem = {
  label: 'Select a Category',
  value: 0,
};

export const initialAssetWorkspace: DropdownItem = {
  label: 'Select a Workspace',
  value: 0,
};

export const initialCreateAssetForm: FormGroup = new FormGroup({
  model: new FormControl('', [Validators.required, Validators.minLength(5)]),
  serialNumber: new FormControl('', [
    Validators.required,
    Validators.minLength(5),
  ]),
  categoryId: new FormControl('', [Validators.required]),
  workspaceId: new FormControl('', [Validators.required]),
  purchaseCost: new FormControl('', [
    Validators.required,
    Validators.minLength(1),
  ]),
  notes: new FormControl('', [Validators.required, Validators.minLength(5)]),
  status: new FormControl('', [Validators.required]),
  purchaseDate: new FormControl('', [Validators.required]),
});
