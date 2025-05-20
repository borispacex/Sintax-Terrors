package com.dharbor.sintaxterrors.asset_service.service;

import com.dharbor.sintaxterrors.asset_service.dto.request.workspace.CreateWorkspaceRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.workspace.GetWorkspacePageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.workspace.UpdateWorkspaceRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.workspace.WorkspaceResponse;
import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.model.entity.workspace.WorkspaceEntity;

import java.util.List;

public interface WorkspaceService {

    WorkspaceResponse saveWorkspace(CreateWorkspaceRequest workspaceRequest);

    boolean isWorkspaceExistInCityAndLocation(BoliviaCity city, String location);

    boolean isWorkspaceExistInCity(BoliviaCity city);

    WorkspaceResponse updateWorkspace(UpdateWorkspaceRequest workspaceRequest);

    boolean isWorkspaceIdExist(Long id);

    WorkspaceResponse getWorkspaceResponseById(Long id);

    WorkspaceEntity getWorkspaceEntityById(Long id);

    WorkspaceResponse getWorkspaceByCity(BoliviaCity city);

    void validateCreateWorkspaceRequest(CreateWorkspaceRequest request);

    void validateUpdateWorkspaceRequest(UpdateWorkspaceRequest request);

    PaginationResponse<WorkspaceResponse> getWorkspacePage(GetWorkspacePageRequest request);

    List<WorkspaceResponse> getWorkspacesByIdList(List<Long> idList);

    List<WorkspaceResponse> getWorkspacesByCity(BoliviaCity city);

    WorkspaceResponse deleteWorkspace(Long id);
}
