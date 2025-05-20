import { createEntityAdapter, EntityState } from '@ngrx/entity';
import { Workspace } from '../../../pages/workspace/interfaces/workspace.interface';
import {
  PaginationMetaData,
  RequestParams,
} from '../../../interfaces/common-response/pagination-meta-data.interface';
export interface WorkspaceState extends EntityState<Workspace> {
  pagination: PaginationMetaData;
  params: RequestParams;
  loading: boolean;
}

export const workspaceEntityAdapter = createEntityAdapter<Workspace>({
  selectId: (entity: Workspace) => entity.id,
  sortComparer: false,
});

export const initialWorkspaceState: WorkspaceState =
  workspaceEntityAdapter.getInitialState({
    ids: [],
    entities: [],
    pagination: {
      totalPages: 0,
      pageSize: 0,
      totalItems: 0,
      currentPage: 0,
    },
    loading: false,
    params: {},
  });
