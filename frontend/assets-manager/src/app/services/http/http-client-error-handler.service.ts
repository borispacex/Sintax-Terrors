import { Injectable } from '@angular/core';
import {
  AbstractException,
  HttpAbstractErrorHandlerService,
} from './http-abstract-error-handler.service';
import { HttpErrorResponse } from '@angular/common/http';
import { HttpStatus } from './http.status.enum';

export abstract class AbstractAManagerException extends AbstractException {
  public readonly code: number = -1;
  public readonly error: string = '';

  constructor(message: string, code: number, error: string) {
    super(message);
    this.code = code;
    this.error = error;
  }
}

export class AManagerNotFoundException extends AbstractAManagerException {
  constructor(message: string, code: number, error: string) {
    super(message, code, error);
  }
}

export class AManagerUnauthorizedException extends AbstractAManagerException {
  constructor(message: string, code: number, error: string) {
    super(message, code, error);
  }
}

export class AManagerForbiddenException extends AbstractAManagerException {
  constructor(message: string, code: number, error: string) {
    super(message, code, error);
  }
}

export class AManagerInternalServerErrorException extends AbstractAManagerException {
  constructor(message: string, code: number, error: string) {
    super(message, code, error);
  }
}

export class AManagerBadRequestException extends AbstractAManagerException {
  constructor(message: string, code: number, error: string) {
    super(message, code, error);
  }
}

export class AManagerConflictException extends AbstractAManagerException {
  constructor(message: string, code: number, error: string) {
    super(message, code, error);
  }
}

@Injectable({
  providedIn: 'root',
})
export class HttpClientErrorHandlerService extends HttpAbstractErrorHandlerService {
  protected handleClientError(error: HttpErrorResponse): any {
    const data = error.error;
    const exception = {
      message: data?.message || '',
      status: error?.status || -1,
      error: data?.error || '',
    };

    if (error.status === HttpStatus.NOT_FOUND) {
      return new AManagerNotFoundException(
        `Resource not found: ${exception.message}`,
        exception.status,
        exception.error
      );
    }

    if (error.status === HttpStatus.UNAUTHORIZED) {
      return new AManagerUnauthorizedException(
        `Unauthorized access: ${exception.message}`,
        exception.status,
        exception.error
      );
    }

    if (error.status === HttpStatus.FORBIDDEN) {
      return new AManagerForbiddenException(
        `Forbidden access: ${exception.message}`,
        exception.status,
        exception.error
      );
    }

    if (error.status === HttpStatus.INTERNAL_SERVER_ERROR) {
      return new AManagerInternalServerErrorException(
        `Internal server error: ${exception.message}`,
        exception.status,
        exception.error
      );
    }

    if (error.status === HttpStatus.BAD_REQUEST) {
      return new AManagerBadRequestException(
        `Bad request: ${exception.message}`,
        exception.status,
        exception.error
      );
    }

    if (error.status === HttpStatus.CONFLICT) {
      return new AManagerConflictException(
        `Conflict: ${exception.message}`,
        exception.status,
        exception.error
      );
    }

    return super.handleClientError(error);
  }
}
