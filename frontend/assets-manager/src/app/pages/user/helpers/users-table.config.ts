import { ServerDataTableHeader } from '../../../modules/shared/server-data-table/interfaces/server-data-table.interface';

export const usersTableHeaders: ServerDataTableHeader[] = [
  { key: 'id', label: 'ID', align: 'center', width: '80px' },
  { key: 'username', label: 'Username', align: 'left', sortable: true },
  {
    key: 'roleName',
    label: 'Role',
    align: 'left',
    sortable: true,
  },
  {
    key: 'state',
    label: 'Status',
    align: 'left',
    width: '150px',
    sortable: true,
  },
];
