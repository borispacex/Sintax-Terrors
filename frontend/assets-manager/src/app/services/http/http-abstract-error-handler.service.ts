import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';

export abstract class AbstractException extends Error {
  constructor(message: string) {
    super(message);
  }
}

export class InterruptedExecutionException extends AbstractException {
  constructor(message: string) {
    super(message);
  }
}

export class UnsupportedOperationException extends AbstractException {
  constructor(message: string) {
    super(message);
  }
}

export interface HTTPErrorHandler {
  handleError(error: HttpErrorResponse): any;
}

export abstract class HttpAbstractErrorHandlerService
  implements HTTPErrorHandler
{
  handleError(error: HttpErrorResponse): any {
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      return throwError(this.handleInternalError(error));
    } else {
      // Server-side error
      return throwError(this.handleClientError(error));
    }
  }

  protected handleInternalError(error: HttpErrorResponse): AbstractException {
    return new InterruptedExecutionException(
      `A client-side error occurred: ${error.message}, please try again later.`
    );
  }

  protected handleClientError(error: HttpErrorResponse): AbstractException {
    return new UnsupportedOperationException(
      `Unhandled error status: ${error.status}, for ${error.url}`
    );
  }
}
