package com.dharbor.sintaxterrors.asset_service.mapper;


import com.dharbor.sintaxterrors.asset_service.dto.request.team.CreateTeamRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.team.UpdateTeamRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.team.TeamResponse;
import com.dharbor.sintaxterrors.asset_service.model.entity.team.TeamEntity;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ResponseConstant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {ResponseConstant.SuccessResponse.class})
public interface TeamMapper {

    TeamMapper mapper = Mappers.getMapper(TeamMapper.class);

    TeamEntity mapperToTeamEntity(CreateTeamRequest source);

    TeamResponse mapperToTeamResponse(TeamEntity source);

    void updateTeamEntity(UpdateTeamRequest source, @MappingTarget TeamEntity target);
}