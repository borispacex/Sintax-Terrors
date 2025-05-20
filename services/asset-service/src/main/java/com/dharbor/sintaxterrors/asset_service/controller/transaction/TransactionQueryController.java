package com.dharbor.sintaxterrors.asset_service.controller.transaction;

import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.command.transaction.GetAssetTransactionHistoryCommand;
import com.dharbor.sintaxterrors.asset_service.command.transaction.GetCurrentAssignedAssetsCommand;
import com.dharbor.sintaxterrors.asset_service.command.transaction.GetTransactionByIdCommand;
import com.dharbor.sintaxterrors.asset_service.command.transaction.GetTransactionDetailsCommand;
import com.dharbor.sintaxterrors.asset_service.controller.constant.TransactionControllerConstants;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.transaction.TransactionDetailResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.transaction.TransactionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Tag(name = TransactionControllerConstants.TransactionSwaggerDoc.TAG_NAME,
        description = TransactionControllerConstants.TransactionSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = TransactionControllerConstants.TransactionPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class TransactionQueryController {

    private final GetTransactionByIdCommand getTransactionByIdCommand;
    private final GetTransactionDetailsCommand getTransactionDetailsCommand;
    private final GetAssetTransactionHistoryCommand getAssetTransactionHistoryCommand;
    private final GetCurrentAssignedAssetsCommand getCurrentAssignedAssetsCommand;

    @Operation(summary = TransactionControllerConstants.TransactionSwaggerDoc.TAG_GET_TRANSACTION_BY_ID)
    @GetMapping("/{id}")
    public CommonResponse<TransactionResponse> getTransactionById(@PathVariable Long id) {
        getTransactionByIdCommand.setInput(id);
        (new SafeCommandExecutor<Long, TransactionResponse>()).safeExecution(getTransactionByIdCommand);
        return new CommonResponse<>(
                getTransactionByIdCommand.getOutput(),
                HttpStatus.OK,
                "Transaction retrieved successfully");
    }

    @Operation(summary = TransactionControllerConstants.TransactionSwaggerDoc.TAG_GET_TRANSACTION_DETAILS)
    @GetMapping("/{id}/details")
    public CommonResponse<List<TransactionDetailResponse>> getTransactionDetails(@PathVariable Long id) {
        getTransactionDetailsCommand.setInput(id);
        (new SafeCommandExecutor<Long, List<TransactionDetailResponse>>()).safeExecution(getTransactionDetailsCommand);
        return new CommonResponse<>(
                getTransactionDetailsCommand.getOutput(),
                HttpStatus.OK,
                "Transaction details retrieved successfully");
    }

    @Operation(summary = TransactionControllerConstants.TransactionSwaggerDoc.TAG_GET_ASSET_HISTORY)
    @GetMapping("/assets/{assetId}/history")
    public CommonResponse<List<TransactionResponse>> getAssetTransactionHistory(@PathVariable Long assetId) {
        getAssetTransactionHistoryCommand.setInput(assetId);
        (new SafeCommandExecutor<Long, List<TransactionResponse>>()).safeExecution(getAssetTransactionHistoryCommand);
        return new CommonResponse<>(
                getAssetTransactionHistoryCommand.getOutput(),
                HttpStatus.OK,
                "Asset transaction history retrieved successfully");
    }

    @Operation(summary = TransactionControllerConstants.TransactionSwaggerDoc.TAG_GET_EMPLOYEE_ASSETS)
    @GetMapping("/employees/{employeeId}/current-assets")
    public CommonResponse<List<AssetResponse>> getCurrentAssignedAssets(@PathVariable Long employeeId) {
        getCurrentAssignedAssetsCommand.setInput(employeeId);
        (new SafeCommandExecutor<Long, List<AssetResponse>>()).safeExecution(getCurrentAssignedAssetsCommand);
        return new CommonResponse<>(
                getCurrentAssignedAssetsCommand.getOutput(),
                HttpStatus.OK,
                "Currently assigned assets retrieved successfully");
    }
}