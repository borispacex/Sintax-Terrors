import { UserInterface } from '../../profile/interfaces/user.interface';

export const initialNotificationTableData = {
  items: [],
  totalItems: 0,
  currentPage: 1,
  pageSize: 10,
  totalPages: 1,
};

export const initialActiveUser: UserInterface = {
  username: '',
  email: '',
  id: 0,
  employeeId: 0,
  role: '',
};
