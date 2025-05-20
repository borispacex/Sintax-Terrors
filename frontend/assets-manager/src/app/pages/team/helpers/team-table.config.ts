import { ServerDataTableHeader } from '../../../modules/shared/server-data-table/interfaces/server-data-table.interface';

export const teamTableHeaders: ServerDataTableHeader[] = [
  { key: 'name', label: 'Team', align: 'left', sortable: true },
  { key: 'manager', label: 'Manager', align: 'left', sortable: true },
  { key: 'description', label: 'Description', align: 'left', sortable: true },
  { key: 'isActive', label: 'Status', align: 'left', sortable: true },
];
