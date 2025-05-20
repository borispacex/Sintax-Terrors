package com.dharbor.sintaxterrors.asset_service.mapper;

import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.AssignmentRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.ExchangeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.ReturnRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.transaction.TransactionDetailResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.transaction.TransactionResponse;
import com.dharbor.sintaxterrors.asset_service.enums.transaction.TransactionType;
import com.dharbor.sintaxterrors.asset_service.model.entity.asset.AssetEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.transaction.TransactionEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.transaction_detail.TransactionDetailEntity;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ResponseConstant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {ResponseConstant.SuccessResponse.class})
public interface TransactionMapper {

    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "employeeName", source = "employee", qualifiedByName = "mapEmployeeName")
    @Mapping(target = "details", source = "transactionDetails", qualifiedByName = "mapDetails")
    TransactionResponse toResponse(TransactionEntity source);

    @Mapping(target = "transactionId", source = "transaction.id")
    @Mapping(target = "assetId", source = "asset.id")
    @Mapping(target = "assetSerialNumber", source = "asset", qualifiedByName = "mapAssetSerialNumber")
    @Mapping(target = "assetModel", source = "asset", qualifiedByName = "mapAssetModel")
    TransactionDetailResponse toDetailResponse(TransactionDetailEntity source);

    @Named("mapEmployeeName")
    default String mapEmployeeName(EmployeeEntity employee) {
        if (employee == null) {
            return null;
        }
        return employee.getFirstName() + " " + employee.getLastName();
    }

    @Named("mapAssetSerialNumber")
    default String mapAssetSerialNumber(AssetEntity asset) {
        return asset != null ? asset.getSerialNumber() : null;
    }

    @Named("mapAssetModel")
    default String mapAssetModel(AssetEntity asset) {
        return asset != null ? asset.getModel() : null;
    }

    @Named("mapDetails")
    default List<TransactionDetailResponse> mapDetails(List<TransactionDetailEntity> details) {
        if (details == null) {
            return null;
        }
        return details.stream()
                .map(this::toDetailResponse)
                .collect(Collectors.toList());
    }

    default TransactionEntity toEntity(AssignmentRequest request, EmployeeEntity employee) {
        TransactionEntity entity = new TransactionEntity();
        entity.setTransactionType(TransactionType.ASSIGNMENT);
        entity.setTransactionDate(java.time.LocalDateTime.now());
        entity.setEmployee(employee);
        entity.setNote(request.getNote());
        return entity;
    }

    default TransactionEntity toEntity(ReturnRequest request, EmployeeEntity employee) {
        TransactionEntity entity = new TransactionEntity();
        entity.setTransactionType(TransactionType.RETURN);
        entity.setTransactionDate(java.time.LocalDateTime.now());
        entity.setEmployee(employee);
        entity.setNote(request.getNote());
        return entity;
    }

    default TransactionEntity toEntity(ExchangeRequest request, EmployeeEntity employee) {
        TransactionEntity entity = new TransactionEntity();
        entity.setTransactionType(TransactionType.EXCHANGE);
        entity.setTransactionDate(java.time.LocalDateTime.now());
        entity.setEmployee(employee);
        entity.setNote(request.getNote());
        return entity;
    }

    default TransactionDetailEntity toDetailEntity(TransactionEntity transaction, AssetEntity asset, String direction, String reason) {
        TransactionDetailEntity detail = new TransactionDetailEntity();
        detail.setTransaction(transaction);
        detail.setAsset(asset);
        detail.setDirection(direction);
        detail.setReason(reason);
        return detail;
    }
}