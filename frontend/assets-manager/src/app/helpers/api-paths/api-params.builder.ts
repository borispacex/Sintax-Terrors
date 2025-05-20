import { RequestParams } from '../../interfaces/common-response/pagination-meta-data.interface';

export interface ApiParams extends Partial<RequestParams> {
  id?: number;
}

export class ParamsBuilder {
  public static build(params?: ApiParams): string {
    if (!params) {
      return '';
    }

    const paramsArray = Object.entries(params).reduce(
      (acc: string[], [key, value]) => {
        if (key !== 'id') {
          acc.push(`${key}=${value}`);
        }
        return acc;
      },
      []
    );

    return paramsArray.length > 0 ? `?${paramsArray.join('&')}` : '';
  }
}
