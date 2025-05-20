package com.dharbor.sintaxterrors.asset_service.mapper;

import com.dharbor.sintaxterrors.asset_service.dto.request.workspace.CreateWorkspaceRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.workspace.UpdateWorkspaceRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.workspace.WorkspaceResponse;
import com.dharbor.sintaxterrors.asset_service.model.entity.workspace.WorkspaceEntity;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ResponseConstant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {ResponseConstant.SuccessResponse.class})
public interface WorkspaceMapper {

    WorkspaceMapper mapper = Mappers.getMapper(WorkspaceMapper.class);

    WorkspaceEntity mapperToWorkspaceEntity(CreateWorkspaceRequest source);

    WorkspaceResponse mapperToWorkspaceResponse(WorkspaceEntity source);

    void updateWorkspaceEntity(UpdateWorkspaceRequest source, @MappingTarget WorkspaceEntity target);
}
