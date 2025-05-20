import { ServerDataTableHeader } from '../../../modules/shared/server-data-table/interfaces/server-data-table.interface';

export const assignmentTableHeaders: ServerDataTableHeader[] = [
  { key: 'id', label: 'ID', align: 'left', width: '90px' },
  { key: 'firstName', label: 'Employee', align: 'left', sortable: true },
  { key: 'personalEmail', label: 'Email', align: 'left', sortable: true },
  { key: 'city', label: 'City', align: 'left', sortable: true },
];
