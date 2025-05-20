package com.dharbor.sintaxterrors.asset_service.service;


import com.dharbor.sintaxterrors.asset_service.dto.request.request.CreateRequestRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.request.GetRequestPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.request.UpdateRequestRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.request.RequestResponse;
import com.dharbor.sintaxterrors.asset_service.enums.shared.Status;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.EmployeeExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.exception.constant.RequestExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.mapper.BaseMapper;
import com.dharbor.sintaxterrors.asset_service.mapper.RequestMapper;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity_;
import com.dharbor.sintaxterrors.asset_service.model.entity.request.RequestEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.request.RequestEntity_;
import com.dharbor.sintaxterrors.asset_service.repository.EmployeeRepository;
import com.dharbor.sintaxterrors.asset_service.repository.RequestRepository;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Specifications;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class RequestServiceImpl implements RequestService {
    private final RequestMapper requestMapper;
    private final RequestRepository requestRepository;
    private final EmployeeRepository employeeRepository;

    private RequestEntity preEntity;
    private EmployeeEntity employeeEntity;

    @Override
    public RequestResponse saveRequest(CreateRequestRequest request) {
        RequestEntity newRequestEntity = requestMapper.mapperToRequestEntity(request);

        if (!Utils.isNull(request.getEmployeeId())) {
            newRequestEntity.setEmployee(this.employeeEntity);
        }

        newRequestEntity.setCreatedAt(LocalDate.now());
        return requestMapper.mapperToRequestResponse(requestRepository.save(newRequestEntity));
    }

    @Override
    public RequestResponse deleteRequest(Integer teamId) {
        RequestEntity requestEntity = getRequestEntityById(teamId);
        requestRepository.delete(requestEntity);
        return requestMapper.mapperToRequestResponse(requestEntity);
    }

    @Override
    public void validateCreateRequestRequest(CreateRequestRequest request) {
        if (!Utils.isNull(request.getEmployeeId())) {
            this.employeeEntity = employeeRepository.findById(request.getEmployeeId())
                    .orElseThrow(() -> new ProcessErrorException(EmployeeExceptionConstants.EMPLOYEE_NOT_FOUND));
        }

        if (Utils.isNull(request.getStatus())) {
            request.setStatus(Status.ACTIVE);
        }
    }

    @Override
    public void validateUpdateRequestRequest(UpdateRequestRequest team) {
        Optional<RequestEntity> requestEntity = requestRepository.findById(team.getId());

        if (!requestEntity.isPresent()) {
            throw new ProcessErrorException(RequestExceptionConstants.REQUEST_NOT_FOUND);
        }

        preEntity = requestEntity.get();
    }

    @Override
    public RequestResponse updateRequest(UpdateRequestRequest request) {
        requestMapper.updateRequestEntity(request, preEntity);
        return requestMapper.mapperToRequestResponse(requestRepository.save(preEntity));
    }

    public RequestEntity getRequestEntityById(Integer requestId) {
        Optional<RequestEntity> requestEntity = requestRepository.findById(requestId);

        if (requestEntity.isEmpty()) {
            throw new ProcessErrorException(RequestExceptionConstants.REQUEST_NOT_FOUND);
        }

        return requestEntity.get();
    }

    @Override
    public RequestResponse findRequestResponseById(Integer teamId) {
        Optional<RequestEntity> requestEntity = requestRepository.findById(teamId);

        if (requestEntity.isEmpty()) {
            throw new ProcessErrorException(RequestExceptionConstants.REQUEST_NOT_FOUND);
        }

        return requestMapper.mapperToRequestResponse(requestEntity.get());
    }

    @Override
    public PaginationResponse<RequestResponse> getRequestPage(GetRequestPageRequest request) {
        List<RequestResponse> requests = new ArrayList<>();
        Sort sort = Specifications.buildSorting(request.getOrder(), request.getDirection());
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Page<RequestEntity> pageResult = requestRepository.findAll(buildSpecification(request), pageRequest);
        requests.addAll(
                pageResult.getContent().stream()
                        .map(requestMapper::mapperToRequestResponse)
                        .collect(Collectors.toList())
        );
        PaginationResponse<RequestResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setItems(requests);
        BaseMapper.setPaginationMetaData(paginationResponse, pageResult);
        return paginationResponse;
    }

    private Specification<RequestEntity> buildSpecification(GetRequestPageRequest request) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!Utils.isNull(request.getTitle())) {
                String[] words = request.getTitle().split(" ");
                for (String word : words) {
                    predicates.add(builder.like(root.get(RequestEntity_.title), "%" + word + "%"));
                }
            }

            if (!Utils.isNull(request.getCreatedAt())) {
                predicates.add(builder.equal(root.get(RequestEntity_.createdAt).as(LocalDate.class),
                        request.getCreatedAt()));
            }

            if (!Utils.isNull(request.getStatus())) {
                predicates.add(builder.equal(root.get(RequestEntity_.status), request.getStatus()));
            }

            if (!Utils.isNull(request.getType())) {
                predicates.add(builder.equal(root.get(RequestEntity_.type), request.getType()));
            }

            if (!Utils.isNull(request.getEmployeeId())) {
                Join<RequestEntity, EmployeeEntity> requestJoinEmployee = root.join(RequestEntity_.employee);
                predicates.add(builder.equal(requestJoinEmployee.get(EmployeeEntity_.id), request.getEmployeeId()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}