package com.dharbor.sintaxterrors.asset_service.service;

import com.dharbor.sintaxterrors.asset_service.dto.request.workspace.CreateWorkspaceRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.workspace.GetWorkspacePageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.workspace.UpdateWorkspaceRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.workspace.WorkspaceResponse;
import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.exception.constant.WorkspaceExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.mapper.BaseMapper;
import com.dharbor.sintaxterrors.asset_service.mapper.WorkspaceMapper;
import com.dharbor.sintaxterrors.asset_service.model.entity.workspace.WorkspaceEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.workspace.WorkspaceEntity_;
import com.dharbor.sintaxterrors.asset_service.repository.WorkspaceRepository;
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
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceMapper workspaceMapper;
    private final WorkspaceRepository workspaceRepository;

    public WorkspaceServiceImpl(
            WorkspaceMapper workspaceMapper,
            WorkspaceRepository workspaceRepository
    ) {
        this.workspaceMapper = workspaceMapper;
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public WorkspaceResponse saveWorkspace(CreateWorkspaceRequest request) {
        validateCreateWorkspaceRequest(request);
        WorkspaceEntity newEntity = workspaceMapper.mapperToWorkspaceEntity(request);
        return workspaceMapper.mapperToWorkspaceResponse(workspaceRepository.save(newEntity));
    }

    @Override
    public boolean isWorkspaceExistInCityAndLocation(BoliviaCity city, String location) {
        return !workspaceRepository.findByCityAndLocation(city, location).isEmpty();
    }

    @Override
    public boolean isWorkspaceExistInCity(BoliviaCity city) {
        return !workspaceRepository.findByCity(city).isEmpty();
    }

    @Override
    public WorkspaceResponse updateWorkspace(UpdateWorkspaceRequest request) {
        validateUpdateWorkspaceRequest(request);
        WorkspaceEntity existing = getWorkspaceEntityById(request.getId());
        workspaceMapper.updateWorkspaceEntity(request, existing);
        return workspaceMapper.mapperToWorkspaceResponse(workspaceRepository.save(existing));
    }

    @Override
    public boolean isWorkspaceIdExist(Long id) {
        return workspaceRepository.findById(id).isPresent();
    }

    @Override
    public WorkspaceResponse getWorkspaceResponseById(Long id) {
        return workspaceMapper.mapperToWorkspaceResponse(getWorkspaceEntityById(id));
    }

    @Override
    public WorkspaceEntity getWorkspaceEntityById(Long id) {
        return workspaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(WorkspaceExceptionConstants.WORKSPACE_NOT_FOUND));
    }

    @Override
    public WorkspaceResponse getWorkspaceByCity(BoliviaCity city) {
        List<WorkspaceEntity> workspaces = workspaceRepository.findByCity(city);
        if (workspaces.isEmpty()) {
            throw new RuntimeException(WorkspaceExceptionConstants.WORKSPACE_NOT_FOUND);
        }
        return workspaceMapper.mapperToWorkspaceResponse(workspaces.get(0));
    }

    @Override
    public void validateCreateWorkspaceRequest(CreateWorkspaceRequest request) {
        if (isWorkspaceExistInCityAndLocation(request.getCity(), request.getLocation())) {
            throw new IllegalArgumentException(WorkspaceExceptionConstants.WORKSPACE_ALREADY_REGISTERED_IN_LOCATION);
        }
    }

    @Override
    public void validateUpdateWorkspaceRequest(UpdateWorkspaceRequest request) {
        if (!isWorkspaceIdExist(request.getId())) {
            throw new IllegalArgumentException(WorkspaceExceptionConstants.WORKSPACE_NOT_FOUND);
        }

        if (isWorkspaceExistInCityAndLocation(request.getCity(), request.getLocation())) {
            throw new IllegalArgumentException(WorkspaceExceptionConstants.WORKSPACE_ALREADY_REGISTERED_IN_LOCATION);
        }
    }

    @Override
    public PaginationResponse<WorkspaceResponse> getWorkspacePage(GetWorkspacePageRequest request) {
        List<WorkspaceResponse> responseList = new ArrayList<>();
        Sort sort = Specifications.buildSortingWorkspaces(request.getOrder(), request.getDirection());
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Page<WorkspaceEntity> pageResult = workspaceRepository.findAll(buildSpecification(request), pageRequest);

        responseList.addAll(
                pageResult.getContent().stream()
                        .map(workspaceMapper::mapperToWorkspaceResponse)
                        .collect(Collectors.toList())
        );

        PaginationResponse<WorkspaceResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setItems(responseList);
        BaseMapper.setPaginationMetaData(paginationResponse, pageResult);
        return paginationResponse;
    }

    private Specification<WorkspaceEntity> buildSpecification(GetWorkspacePageRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!Utils.isNull(request.getSearch())) {
                predicates.add(criteriaBuilder.equal(
                        root.get(WorkspaceEntity_.city),
                        BoliviaCity.valueOf(request.getSearch().toUpperCase())
                ));
            }

            if (!Utils.isNull(request.getCity())) {
                predicates.add(criteriaBuilder.equal(root.get(WorkspaceEntity_.city), request.getCity()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public List<WorkspaceResponse> getWorkspacesByIdList(List<Long> idList) {
        if (idList == null || idList.isEmpty()) {
            throw new IllegalArgumentException(WorkspaceExceptionConstants.NO_EMPTY_LIST_MESSAGE);
        }
        return workspaceRepository.findByIdIn(idList).stream()
                .map(workspaceMapper::mapperToWorkspaceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkspaceResponse> getWorkspacesByCity(BoliviaCity city) {
        return workspaceRepository.findByCity(city).stream()
                .map(workspaceMapper::mapperToWorkspaceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public WorkspaceResponse deleteWorkspace(Long id) {
        WorkspaceEntity workspaceEntity = getWorkspaceEntityById(id);
        workspaceRepository.delete(workspaceEntity);
        return workspaceMapper.mapperToWorkspaceResponse(workspaceEntity);
    }
}
