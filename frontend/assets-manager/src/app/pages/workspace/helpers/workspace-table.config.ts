import { ServerDataTableHeader } from '../../../modules/shared/server-data-table/interfaces/server-data-table.interface';

export const workspaceTableHeaders: ServerDataTableHeader[] = [
  { key: 'id', label: 'ID', align: 'center', width: '80px' },
  { key: 'location', label: 'Location', align: 'left', sortable: true },
  {
    key: 'city',
    label: 'City',
    align: 'left',
    width: '150px',
    sortable: true,
  },
];
