import { UserInterface } from '../../modules/shared/profile/interfaces/user.interface';

export interface UserState {
  user: UserInterface | null;
  loading: boolean;
}
