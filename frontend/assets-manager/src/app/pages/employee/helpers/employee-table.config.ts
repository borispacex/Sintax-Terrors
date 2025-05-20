import { ServerDataTableHeader } from '../../../modules/shared/server-data-table/interfaces/server-data-table.interface';

export const employeeTableHeaders: ServerDataTableHeader[] = [
  { key: 'name', label: 'Name', align: 'left', sortable: true },
  { key: 'ci', label: 'CI', align: 'left', sortable: true },
  { key: 'city', label: 'City', align: 'left', sortable: true },
  { key: 'team', label: 'Team', align: 'left', sortable: true },
  { key: 'status', label: 'Status', align: 'left', sortable: true },
];
