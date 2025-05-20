export interface UserInterface {
  username: string;
  email: string;
  id: number | null;
  employeeId: number | null;
  role: string;
}

export enum RoleEnum {
  ADMIN = 'ADMIN',
  USER = 'USER',
  RRHH = 'RRHH',
  IT = 'IT',
}
