package com.dharbor.sintaxterrors.asset_service.service;


import com.dharbor.sintaxterrors.asset_service.dto.request.team.CreateTeamRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.team.GetTeamPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.team.UpdateTeamRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.team.TeamResponse;
import com.dharbor.sintaxterrors.asset_service.model.entity.team.TeamEntity;

public interface TeamService {
    TeamResponse saveTeam(CreateTeamRequest createTeamRequest);

    void validateCreateTeamRequest(CreateTeamRequest team);

    void validateUpdateTeamRequest(UpdateTeamRequest team);

    void basicTeamValidation(Integer projectManagerId, Integer teamId);

    Boolean existTeamByName(String name, Integer id);

    TeamResponse updateTeam(UpdateTeamRequest updateTeamRequest);

    TeamResponse deleteTeam(Integer id);

    TeamEntity getTeamById(Integer teamId);

    TeamResponse findTeamById(Integer input);

    PaginationResponse<TeamResponse> getTeamPage(GetTeamPageRequest input);
}