package com.dharbor.sintaxterrors.asset_service.service;

import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.AssignmentRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.ExchangeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.GetTransactionPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.ReturnRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.transaction.TransactionDetailResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.transaction.TransactionResponse;
import com.dharbor.sintaxterrors.asset_service.enums.asset.AssetStatus;
import com.dharbor.sintaxterrors.asset_service.enums.transaction.TransactionType;
import com.dharbor.sintaxterrors.asset_service.exception.constant.AssetExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.exception.constant.EmployeeExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.exception.constant.TransactionExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.mapper.AssetMapper;
import com.dharbor.sintaxterrors.asset_service.mapper.BaseMapper;
import com.dharbor.sintaxterrors.asset_service.mapper.TransactionMapper;
import com.dharbor.sintaxterrors.asset_service.model.entity.asset.AssetEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.transaction.TransactionEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.transaction.TransactionEntity_;
import com.dharbor.sintaxterrors.asset_service.model.entity.transaction_detail.TransactionDetailEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.transaction_detail.TransactionDetailEntity_;
import com.dharbor.sintaxterrors.asset_service.repository.AssetRepository;
import com.dharbor.sintaxterrors.asset_service.repository.EmployeeRepository;
import com.dharbor.sintaxterrors.asset_service.repository.TransactionDetailRepository;
import com.dharbor.sintaxterrors.asset_service.repository.TransactionRepository;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Specifications;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class TransactionServiceImpl implements TransactionService {

    private final TransactionMapper transactionMapper;
    private final AssetMapper assetMapper;
    private final TransactionRepository transactionRepository;
    private final TransactionDetailRepository transactionDetailRepository;
    private final EmployeeRepository employeeRepository;
    private final AssetRepository assetRepository;

    public TransactionServiceImpl(
            TransactionMapper transactionMapper,
            AssetMapper assetMapper,
            TransactionRepository transactionRepository,
            TransactionDetailRepository transactionDetailRepository,
            EmployeeRepository employeeRepository,
            AssetRepository assetRepository) {
        this.transactionMapper = transactionMapper;
        this.assetMapper = assetMapper;
        this.transactionRepository = transactionRepository;
        this.transactionDetailRepository = transactionDetailRepository;
        this.employeeRepository = employeeRepository;
        this.assetRepository = assetRepository;
    }

    @Override
    @Transactional
    public TransactionResponse createAssignment(AssignmentRequest request) {
        validateAssignmentRequest(request);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionType(TransactionType.ASSIGNMENT);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setEmployee(employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException(EmployeeExceptionConstants.EMPLOYEE_NOT_FOUND)));
        transaction.setNote(request.getNote());

        TransactionEntity savedTransaction = transactionRepository.save(transaction);

        List<TransactionDetailEntity> details = new ArrayList<>();
        for (Long assetId : request.getAssetIds()) {
            AssetEntity asset = assetRepository.findById(assetId)
                    .orElseThrow(() -> new EntityNotFoundException(AssetExceptionConstants.ASSET_NOT_FOUND + assetId));

            asset.setStatus(AssetStatus.ASIGNED);
            assetRepository.save(asset);
            TransactionDetailEntity detail = new TransactionDetailEntity();
            detail.setTransaction(savedTransaction);
            detail.setAsset(asset);
            detail.setDirection("OUT");
            detail.setReason(request.getReason());

            details.add(detail);
        }

        transactionDetailRepository.saveAll(details);

        return transactionMapper.toResponse(savedTransaction);
    }

    @Override
    @Transactional
    public TransactionResponse createReturn(ReturnRequest request) {
        validateReturnRequest(request);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionType(TransactionType.RETURN);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setEmployee(employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException(EmployeeExceptionConstants.EMPLOYEE_NOT_FOUND)));
        transaction.setNote(request.getNote());

        TransactionEntity savedTransaction = transactionRepository.save(transaction);

        List<TransactionDetailEntity> details = new ArrayList<>();
        for (Long assetId : request.getAssetIds()) {
            AssetEntity asset = assetRepository.findById(assetId)
                    .orElseThrow(() -> new EntityNotFoundException(AssetExceptionConstants.ASSET_NOT_FOUND + assetId));

            asset.setStatus(AssetStatus.AVAILABLE);
            assetRepository.save(asset);
            TransactionDetailEntity detail = new TransactionDetailEntity();
            detail.setTransaction(savedTransaction);
            detail.setAsset(asset);
            detail.setDirection("IN");
            detail.setReason(request.getReason());

            details.add(detail);
        }

        transactionDetailRepository.saveAll(details);

        return transactionMapper.toResponse(savedTransaction);
    }

    @Override
    @Transactional
    public TransactionResponse createExchange(ExchangeRequest request) {
        validateExchangeRequest(request);

        TransactionEntity transaction = new TransactionEntity();
        transaction.setTransactionType(TransactionType.EXCHANGE);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setEmployee(employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException(EmployeeExceptionConstants.EMPLOYEE_NOT_FOUND)));
        transaction.setNote(request.getNote());

        TransactionEntity savedTransaction = transactionRepository.save(transaction);

        List<TransactionDetailEntity> details = new ArrayList<>();

        for (Long returnedAssetId : request.getReturnedAssetIds()) {
            AssetEntity asset = assetRepository.findById(returnedAssetId)
                    .orElseThrow(() -> new EntityNotFoundException(AssetExceptionConstants.ASSET_NOT_FOUND + returnedAssetId));

            asset.setStatus(AssetStatus.AVAILABLE);
            assetRepository.save(asset);
            TransactionDetailEntity detail = new TransactionDetailEntity();
            detail.setTransaction(savedTransaction);
            detail.setAsset(asset);
            detail.setDirection("IN");
            detail.setReason(request.getReturnReason());

            details.add(detail);
        }

        for (Long newAssetId : request.getNewAssetIds()) {
            AssetEntity asset = assetRepository.findById(newAssetId)
                    .orElseThrow(() -> new EntityNotFoundException(AssetExceptionConstants.ASSET_NOT_FOUND + newAssetId));

            asset.setStatus(AssetStatus.ASIGNED);
            assetRepository.save(asset);
            TransactionDetailEntity detail = new TransactionDetailEntity();
            detail.setTransaction(savedTransaction);
            detail.setAsset(asset);
            detail.setDirection("OUT");
            detail.setReason(request.getAssignmentReason());

            details.add(detail);
        }

        transactionDetailRepository.saveAll(details);

        return transactionMapper.toResponse(savedTransaction);
    }

    @Override
    public TransactionResponse getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException(TransactionExceptionConstants.TRANSACTION_NOT_FOUND));
    }

    @Override
    public boolean existsById(Long id) {
        return transactionRepository.findById(id).isPresent();
    }

    @Override
    public PaginationResponse<TransactionResponse> getTransactionPage(GetTransactionPageRequest request) {
        Sort sort = Specifications.buildSorting(request.getOrder(), request.getDirection());
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);

        Page<TransactionEntity> pageResult = transactionRepository.findAll(buildSpecification(request), pageRequest);

        List<TransactionResponse> transactions = pageResult.getContent().stream()
                .map(transactionMapper::toResponse)
                .collect(Collectors.toList());

        PaginationResponse<TransactionResponse> response = new PaginationResponse<>();
        response.setItems(transactions);
        BaseMapper.setPaginationMetaData(response, pageResult);
        return response;
    }

    private Specification<TransactionEntity> buildSpecification(GetTransactionPageRequest request) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!Utils.isNull(request.getTransactionType())) {
                predicates.add(builder.equal(
                        root.get(TransactionEntity_.transactionType), request.getTransactionType()
                ));
            }

            if (!Utils.isNull(request.getEmployeeId())) {
                predicates.add(builder.equal(
                        root.get(TransactionEntity_.employee).get("id"), request.getEmployeeId()
                ));
            }

            if (!Utils.isNull(request.getNote())) {
                predicates.add(builder.like(
                        builder.lower(root.get(TransactionEntity_.note)),
                        "%" + request.getNote().toLowerCase() + "%"
                ));
            }

            if (!Utils.isNull(request.getStartDate()) && !Utils.isNull(request.getEndDate())) {
                predicates.add(builder.between(
                        root.get(TransactionEntity_.transactionDate),
                        request.getStartDate(),
                        request.getEndDate()
                ));
            } else if (!Utils.isNull(request.getStartDate())) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(TransactionEntity_.transactionDate),
                        request.getStartDate()
                ));
            } else if (!Utils.isNull(request.getEndDate())) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(TransactionEntity_.transactionDate),
                        request.getEndDate()
                ));
            }

            if (!Utils.isNull(request.getAssetId())) {
                Join<TransactionEntity, TransactionDetailEntity> details = root.join(TransactionEntity_.transactionDetails);
                predicates.add(builder.equal(details.get(TransactionDetailEntity_.asset).get("id"), request.getAssetId()));
            }

            query.distinct(true);

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public List<TransactionResponse> getTransactionsByEmployee(Long employeeId) {
        return transactionRepository.findByEmployeeId(employeeId).stream()
                .map(transactionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDetailResponse> getTransactionDetails(Long transactionId) {
        return transactionDetailRepository.findByTransactionId(transactionId).stream()
                .map(transactionMapper::toDetailResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionResponse> getAssetTransactionHistory(Long assetId) {
        return transactionRepository.findAssetMovementHistory(assetId).stream()
                .map(transactionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssetResponse> getCurrentAssignedAssets(Long employeeId) {
        List<AssetEntity> assets = transactionRepository.findCurrentlyAssignedAssetsByEmployee(employeeId);
        if (assets == null) {
            return Collections.emptyList();
        }
        return assets.stream()
                .filter(Objects::nonNull)
                .map(assetMapper::mapperToAssetResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void validateAssignmentRequest(AssignmentRequest request) {
        if (Utils.isNull(request.getAssetIds())) {
            throw new IllegalArgumentException(TransactionExceptionConstants.EMPTY_ASSET_LIST);
        }

        for (Long assetId : request.getAssetIds()) {
            if (!assetRepository.existsById(assetId)) {
                throw new EntityNotFoundException(AssetExceptionConstants.ASSET_NOT_FOUND + assetId);
            }
        }

        if (!employeeRepository.existsById(request.getEmployeeId())) {
            throw new EntityNotFoundException(EmployeeExceptionConstants.EMPLOYEE_NOT_FOUND);
        }
    }

    @Override
    public void validateReturnRequest(ReturnRequest request) {
        if (Utils.isNull(request.getAssetIds())) {
            throw new IllegalArgumentException(TransactionExceptionConstants.EMPTY_ASSET_LIST);
        }

        for (Long assetId : request.getAssetIds()) {
            if (!assetRepository.existsById(assetId)) {
                throw new EntityNotFoundException(AssetExceptionConstants.ASSET_NOT_FOUND + assetId);
            }
        }

        if (!employeeRepository.existsById(request.getEmployeeId())) {
            throw new EntityNotFoundException(EmployeeExceptionConstants.EMPLOYEE_NOT_FOUND);
        }
    }

    @Override
    public void validateExchangeRequest(ExchangeRequest request) {
        if (Utils.isNull(request.getReturnedAssetIds()) || Utils.isNull(request.getNewAssetIds())) {
            throw new IllegalArgumentException(TransactionExceptionConstants.EMPTY_EXCHANGE_LIST);
        }

        validateReturnRequest(new ReturnRequest(request.getEmployeeId(), request.getReturnedAssetIds(), request.getReturnReason(), ""));
        validateAssignmentRequest(new AssignmentRequest(request.getEmployeeId(), request.getNewAssetIds(), request.getAssignmentReason(), ""));
    }

}