import { AssignmentEmployee } from '../interfaces/assignment-employee.interface';
import { BoliviaCity } from '../../../interfaces/enums/bolivia-city.enum';
import { EmployeeStatus } from '../../../interfaces/enums/employee-status.enum';
import { Employee } from '../../employee/interfaces/employee.interface';

export class EmployeeBuilder {
  public static build(item: AssignmentEmployee): Employee {
    return {
      id: item.id,
      ci: item.ci,
      firstName: item.firstName,
      middleName: item.middleName,
      lastName: item.lastName,
      secondLastName: item.secondLastName,
      personalEmail: item.personalEmail,
      workEmail: item.workEmail,
      birthDate: item.birthDate,
      country: item.country,
      city: item.city as BoliviaCity,
      cellphoneNumber: item.cellphoneNumber,
      status: item.status as EmployeeStatus,
    };
  }
}
