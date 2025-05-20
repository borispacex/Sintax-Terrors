import { ServerDataTableHeader } from '../../../modules/shared/server-data-table/interfaces/server-data-table.interface';

export const requestTableHeaders: ServerDataTableHeader[] = [
  { key: 'employeeName', label: 'Name', align: 'left', sortable: true },
  { key: 'title', label: 'Title', align: 'left', sortable: true },
  { key: 'type', label: 'Type', align: 'left', sortable: true },
  { key: 'status', label: 'Status', align: 'left', sortable: true },
];
