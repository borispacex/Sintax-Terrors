import { Request, RequestTable } from '../interfaces/request.interface';

export class RequestTableBuilder {
  public static build(items: Request[]): RequestTable[] {
    return items.map(item => ({
      employeeName: item.employee.firstName + ' ' + item.employee.lastName,
      title: item.title,
      type: item.type,
      status: item.status,
      request: item,
    }));
  }
}
