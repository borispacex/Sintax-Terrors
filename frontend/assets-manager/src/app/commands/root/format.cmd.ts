import { Observable } from 'rxjs';

type CommandExecuteMethod0<T> = () => Observable<T>;

type CommandExecuteMethod1<T, A> = (arg: A) => Observable<T>;

type CommandExecuteMethod2<T, A, B> = (arg1: A, arg2: B) => Observable<T>;

type CommandExecuteMethod3<T, A, B, C> = (
  arg1: A,
  arg2: B,
  arg3: C
) => Observable<T>;

type CommandExecuteMethod4<T, A, B, C, D> = (
  arg1: A,
  arg2: B,
  arg3: C,
  arg4: D
) => Observable<T>;

export interface FormatCommand<T, A = any, B = any, C = any, D = any> {
  execute:
    | CommandExecuteMethod0<T>
    | CommandExecuteMethod1<T, A>
    | CommandExecuteMethod2<T, A, B>
    | CommandExecuteMethod3<T, A, B, C>
    | CommandExecuteMethod4<T, A, B, C, D>;
}
