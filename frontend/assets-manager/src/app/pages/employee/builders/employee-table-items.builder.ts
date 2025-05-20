import { Employee, EmployeeTable } from '../interfaces/employee.interface';

export class EmployeeTableBuilder {
  public static build(items: Employee[]): EmployeeTable[] {
    return items.map(item => ({
      id: item.id,
      name: item.firstName + ' ' + item.lastName,
      ci: item.ci,
      city: item.city,
      team: item.team?.name || 'No team assigned',
      status: item.status,
      employee: item,
    }));
  }
}
