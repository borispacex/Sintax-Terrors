package com.dharbor.sintaxterrors.asset_service.service;

import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.AssignmentRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.ExchangeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.GetTransactionPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.ReturnRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.transaction.TransactionDetailResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.transaction.TransactionResponse;

import java.util.List;

public interface TransactionService {

    TransactionResponse createAssignment(AssignmentRequest request);

    TransactionResponse createReturn(ReturnRequest request);

    TransactionResponse createExchange(ExchangeRequest request);

    TransactionResponse getTransactionById(Long id);

    boolean existsById(Long id);

    PaginationResponse<TransactionResponse> getTransactionPage(GetTransactionPageRequest request);

    List<TransactionResponse> getTransactionsByEmployee(Long employeeId);

    List<TransactionDetailResponse> getTransactionDetails(Long transactionId);

    List<TransactionResponse> getAssetTransactionHistory(Long assetId);

    List<AssetResponse> getCurrentAssignedAssets(Long employeeId);

    void validateAssignmentRequest(AssignmentRequest request);

    void validateReturnRequest(ReturnRequest request);

    void validateExchangeRequest(ExchangeRequest request);
}
