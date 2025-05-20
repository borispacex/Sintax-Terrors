import { ServerDataTableHeader } from '../../../modules/shared/server-data-table/interfaces/server-data-table.interface';

export const assetTableHeaders: ServerDataTableHeader[] = [
  { key: 'id', label: 'Tracking ID', align: 'center', width: '80px' },
  { key: 'model', label: 'Model', align: 'center', sortable: true },
  {
    key: 'serialNumber',
    label: 'Serial Number',
    align: 'center',
    width: '150px',
    sortable: true,
  },
  {
    key: 'categoryId',
    label: 'Category',
    align: 'center',
    width: '100px',
    sortable: true,
  },
  {
    key: 'purchaseDate',
    label: 'Purchase Date',
    align: 'center',
    width: '50px',
    sortable: true,
  },
  {
    key: 'purchaseCost',
    label: 'Current Value',
    align: 'center',
    width: '50px',
    sortable: true,
  },
  {
    key: 'status',
    label: 'Status',
    align: 'center',
    width: '50px',
    sortable: true,
  },
];
