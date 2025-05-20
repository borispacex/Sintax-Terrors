package com.dharbor.sintaxterrors.asset_service.service;


import com.dharbor.sintaxterrors.asset_service.dto.request.team.CreateTeamRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.team.GetTeamPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.team.UpdateTeamRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.team.TeamResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.TeamExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.mapper.BaseMapper;
import com.dharbor.sintaxterrors.asset_service.mapper.TeamMapper;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.team.TeamEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.team.TeamEntity_;
import com.dharbor.sintaxterrors.asset_service.repository.EmployeeRepository;
import com.dharbor.sintaxterrors.asset_service.repository.TeamRepository;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Specifications;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class TeamServiceImpl implements TeamService {
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    private TeamEntity preEntity;
    private EmployeeEntity projectManager;

    public TeamServiceImpl(
            TeamMapper teamMapper,
            TeamRepository teamRepository,
            EmployeeRepository employeeRepository
    ) {
        this.teamMapper = teamMapper;
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public TeamResponse saveTeam(CreateTeamRequest team) {
        TeamEntity newTeamEntity = teamMapper.mapperToTeamEntity(team);
        newTeamEntity.setProjectManager(this.projectManager);
        return teamMapper.mapperToTeamResponse(teamRepository.save(newTeamEntity));
    }

    @Override
    public TeamResponse deleteTeam(Integer teamId) {
        TeamEntity teamEntity = getTeamById(teamId);
        teamRepository.delete(teamEntity);
        return teamMapper.mapperToTeamResponse(teamEntity);
    }

    @Override
    public void validateCreateTeamRequest(CreateTeamRequest team) {
        if (existTeamByName(team.getName(), null)) {
            throw new ProcessErrorException(TeamExceptionConstants.TEAM_ALREADY_REGISTERED);
        }

        if (Utils.isNull(team.getIsActive())) {
            team.setIsActive(true);
        }

        basicTeamValidation(team.getProjectManagerId(), null);
    }

    @Override
    public void validateUpdateTeamRequest(UpdateTeamRequest team) {
        Optional<TeamEntity> teamEntity = teamRepository.findById(team.getId());

        if (!teamEntity.isPresent()) {
            throw new ProcessErrorException(TeamExceptionConstants.TEAM_NOT_FOUND);
        }

        preEntity = teamEntity.get();
        basicTeamValidation(team.getProjectManagerId(), team.getId());
    }

    @Override
    public void basicTeamValidation(Integer projectManagerId, Integer teamId) {
        EmployeeEntity projectManager = employeeRepository.findById(projectManagerId).orElseThrow(() ->
                new ProcessErrorException(String.format(TeamExceptionConstants.PROJECT_MANAGER_NOT_FOUND, projectManagerId)));

        if (!Utils.isNull(projectManager.getProjectManagerTeam()) && projectManager.getProjectManagerTeam().getId() != teamId) {
            throw new ProcessErrorException(TeamExceptionConstants.PROJECT_MANAGER_IS_ASSIGNED_TO_ANOTHER_TEAM);
        }

        this.projectManager = projectManager;
    }

    @Override
    public Boolean existTeamByName(String name, Integer id) {
        Optional<TeamEntity> teamEntity = teamRepository.findByName(name);
        return teamEntity.isPresent() && !teamEntity.get().getId().equals(id);
    }


    @Override
    public TeamResponse updateTeam(UpdateTeamRequest team) {
        if (existTeamByName(team.getName(), team.getId())) {
            throw new ProcessErrorException(TeamExceptionConstants.TEAM_ALREADY_REGISTERED);
        }
        preEntity.setProjectManager(this.projectManager);
        teamMapper.updateTeamEntity(team, preEntity);
        return teamMapper.mapperToTeamResponse(teamRepository.save(preEntity));
    }

    @Override
    public TeamEntity getTeamById(Integer teamId) {
        Optional<TeamEntity> teamEntity = teamRepository.findById(teamId);
        if (teamEntity.isEmpty()) {
            throw new ProcessErrorException(TeamExceptionConstants.TEAM_NOT_FOUND);
        }
        return teamEntity.get();
    }

    @Override
    public TeamResponse findTeamById(Integer teamId) {
        Optional<TeamEntity> teamEntity = teamRepository.findById(teamId);

        if (teamEntity.isEmpty()) {
            throw new ProcessErrorException(TeamExceptionConstants.TEAM_NOT_FOUND);
        }

        teamRepository.deleteById(Long.valueOf(teamId));
        return teamMapper.mapperToTeamResponse(teamEntity.get());
    }

    @Override
    public PaginationResponse<TeamResponse> getTeamPage(GetTeamPageRequest request) {
        List<TeamResponse> team = new ArrayList<>();
        Sort sort = Specifications.buildSorting(request.getOrder(), request.getDirection());
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Page<TeamEntity> pageResult = teamRepository.findAll(buildSpecification(request), pageRequest);
        team.addAll(
                pageResult.getContent().stream()
                        .map(teamMapper::mapperToTeamResponse)
                        .collect(Collectors.toList())
        );
        PaginationResponse<TeamResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setItems(team);
        BaseMapper.setPaginationMetaData(paginationResponse, pageResult);
        return paginationResponse;
    }

    private Specification<TeamEntity> buildSpecification(GetTeamPageRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!Utils.isNull(request.getName())) {
                String[] words = request.getName().split(" ");
                for (String word : words) {
                    predicates.add(criteriaBuilder.like(root.get(TeamEntity_.name), "%" + word + "%"));
                }
            }
            if (!Utils.isNull(request.getIsActive())) {
                predicates.add(criteriaBuilder.equal(root.get(TeamEntity_.isActive), request.getIsActive()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}