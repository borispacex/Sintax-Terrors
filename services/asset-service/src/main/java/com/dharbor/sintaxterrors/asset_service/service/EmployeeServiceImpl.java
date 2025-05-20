package com.dharbor.sintaxterrors.asset_service.service;

import com.dharbor.sintaxterrors.asset_service.dto.request.employee.CreateEmployeeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.employee.GetEmployeePageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.employee.UpdateEmployeeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeShortResponse;
import com.dharbor.sintaxterrors.asset_service.exception.EmployeeRestException;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.EmployeeExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.exception.constant.TeamExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.mapper.BaseMapper;
import com.dharbor.sintaxterrors.asset_service.mapper.EmployeeMapper;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity_;
import com.dharbor.sintaxterrors.asset_service.model.entity.team.TeamEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.team.TeamEntity_;
import com.dharbor.sintaxterrors.asset_service.repository.EmployeeRepository;
import com.dharbor.sintaxterrors.asset_service.repository.TeamRepository;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Specifications;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;
    private final TeamService teamService;

    private TeamEntity teamEntity;

    private EmployeeEntity employeeEntity;

    public EmployeeServiceImpl(
            EmployeeMapper employeeMapper,
            EmployeeRepository employeeRepository,
            TeamRepository teamRepository,
            TeamService teamService
    ) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    @Override
    public EmployeeResponse saveEmployee(CreateEmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntity = employeeMapper.mapperToEmployeeEntityFrom(employeeRequest);

        if (!Utils.isNull(employeeRequest.getTeamId())) {
            employeeEntity.setTeam(this.teamEntity);
        }

        return employeeMapper.mapperToEmployeeResponseFrom(employeeRepository.save(employeeEntity));
    }

    @Override
    public EmployeeResponse updateEmployee(UpdateEmployeeRequest employeeRequest) {
        employeeMapper.mapperToUpdateEmployeeRequestFrom(employeeRequest);

        EmployeeEntity preEntity = this.employeeEntity;
        this.employeeMapper.updateEmployeeRequestFrom(employeeRequest, preEntity);

        if (!Utils.isNull(employeeRequest.getTeamId())) {
            preEntity.setTeam(this.teamEntity);
        } else {
            preEntity.setTeam(null);
        }

        return employeeMapper.mapperToEmployeeResponseFrom(employeeRepository.save(preEntity));
    }

    @Override
    public boolean isEmployeeCiExist(String employeeCi) {
        return employeeRepository.findByCi(employeeCi)
                .map(employeeEntity -> true)
                .orElse(false);
    }

    @Override
    public boolean isEmployeeIdExist(Integer id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return employeeEntity.isPresent();
    }

    @Override
    public EmployeeResponse getEmployeeResponseById(Integer id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return employeeMapper.mapperToEmployeeResponseFrom(employeeEntity.get());
    }

    @Override
    public EmployeeEntity getEmployeeEntityById(Integer id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new ProcessErrorException(EmployeeExceptionConstants.EMPLOYEE_NOT_FOUND));

        return employeeEntity;
    }

    @Override
    public EmployeeResponse getEmployeeByCi(String employeeCi) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findByCi(employeeCi);
        if (employeeEntity.isPresent()) {
            return employeeMapper.mapperToEmployeeResponseFrom(employeeEntity.get());
        }
        return new EmployeeResponse();
    }

    public boolean isUserIdExist(Integer userId) {
        return employeeRepository.findByUserId(userId)
                .map(employeeEntity -> true)
                .orElse(false);
    }

    @Override
    public void validateCreateEmployeeRequest(CreateEmployeeRequest request) {
        if (isEmployeeCiExist(request.getCi())) {
            throw new ProcessErrorException(EmployeeExceptionConstants.EMPLOYEE_ALREADY_REGISTERED);
        }

        if (!Utils.isNull(request.getTeamId())) {
            Optional<TeamEntity> teamEntity = teamRepository.findById(request.getTeamId());

            if (!teamEntity.isPresent()) {
                throw new ProcessErrorException(TeamExceptionConstants.TEAM_NOT_FOUND);
            }
        }
    }

    @Override
    public EmployeeShortResponse getShortEmployeeByUserId(Integer userId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findByUserId(userId);
        if (employeeEntity.isPresent()) {
            return employeeMapper.mapperToShortEmployeeResponseFrom(employeeEntity.get());
        } else {
            return new EmployeeShortResponse();
        }
    }

    @Override
    public void validateUpdateEmployeeRequest(UpdateEmployeeRequest request) {
        this.employeeEntity = employeeRepository.findById(request.getId()).orElseThrow(
                () -> new ProcessErrorException(EmployeeExceptionConstants.EMPLOYEE_NOT_FOUND)
        );

        EmployeeResponse employeeCiResponse = getEmployeeByCi(request.getCi());
        if (!Utils.isNull(employeeCiResponse.getId())) {
            if (employeeCiResponse.getId() != request.getId()) {
                throw new EmployeeRestException(EmployeeExceptionConstants.CI_ALREADY_REGISTERED);
            }
        }

        if (!Utils.isNull(request.getTeamId())) {
            this.teamEntity = teamRepository.findById(request.getTeamId()).orElseThrow(
                    () -> new ProcessErrorException(TeamExceptionConstants.TEAM_NOT_FOUND)
            );
        } else {
            this.teamEntity = employeeEntity.getTeam();
        }
    }

    @Override
    public PaginationResponse<EmployeeResponse> getEmployeePage(GetEmployeePageRequest request) {
        List<EmployeeResponse> employees = new ArrayList<>();
        Sort sort = Specifications.buildSorting(request.getOrder(), request.getDirection());
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Page<EmployeeEntity> pageResult = employeeRepository.findAll(buildSpecification(request), pageRequest);
        employees.addAll(
                pageResult.getContent().stream()
                        .map(employeeEntity -> employeeMapper.mapperToEmployeeResponseFrom(employeeEntity))
                        .collect(Collectors.toList())
        );
        PaginationResponse<EmployeeResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setItems(employees);
        BaseMapper.setPaginationMetaData(paginationResponse, pageResult);
        return paginationResponse;
    }

    @Override
    public PaginationResponse<EmployeeResponse> getEmployeeWithTransactionPage(GetEmployeePageRequest request) {
        Sort sort = Specifications.buildSorting(request.getOrder(), request.getDirection());
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);

        Page<EmployeeEntity> pageResult;
        pageResult = employeeRepository.findAllWithTransactions(pageRequest);

        List<EmployeeResponse> employees = pageResult.getContent().stream()
                .map(employeeMapper::mapperToEmployeeResponseFrom)
                .collect(Collectors.toList());

        PaginationResponse<EmployeeResponse> response = new PaginationResponse<>();
        response.setItems(employees);
        BaseMapper.setPaginationMetaData(response, pageResult);

        return response;
    }

    private Specification<EmployeeEntity> buildSpecification(GetEmployeePageRequest request) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!Utils.isNull(request.getStatus())) {
                predicates.add(builder.equal(root.get(EmployeeEntity_.status), request.getStatus()));
            }

            if (!Utils.isNull(request.getCity())) {
                predicates.add(builder.equal(root.get(EmployeeEntity_.city), request.getCity()));
            }

            if (!Utils.isNull(request.getCountry())) {
                predicates.add(builder.equal(root.get(EmployeeEntity_.country), request.getCountry()));
            }

            if (request.getSearch() != null) {
                String newSearch = request.getSearch();
                String[] words = newSearch.split(" ");
                for (String word : words) {
                    word = "%" + word + "%";
                    predicates.add(builder.or(
                            builder.like(root.get(EmployeeEntity_.firstName), word),
                            builder.like(root.get(EmployeeEntity_.middleName), word),
                            builder.like(root.get(EmployeeEntity_.lastName), word),
                            builder.like(root.get(EmployeeEntity_.secondLastName), word)
                    ));
                }
            }

            if (!Utils.isNull(request.getTeamId())) {
                Join<EmployeeEntity, TeamEntity> employeeJoinTeam = root.join(EmployeeEntity_.team);
                predicates.add(builder.equal(employeeJoinTeam.get(TeamEntity_.id), request.getTeamId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public List<EmployeeResponse> getEmployeesByIdList(List<Integer> idList) {
        List<EmployeeEntity> employeeEntities = employeeRepository.findByIdIn(idList);
        return employeeEntities.stream()
                .map(
                        employeeEntity -> employeeMapper.mapperToEmployeeResponseFrom(employeeEntity)
                )
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse getEmployeesByIdUser(Integer idUser) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findByUserId(idUser);

        return employeeMapper.mapperToEmployeeResponseFrom(employeeEntity.get());
    }

    @Override
    public boolean isIdUserRegister(Integer id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findByUserId(id);
        return employeeEntity.isPresent();
    }

}
