package com.dharbor.sintaxterrors.asset_service.controller.transaction;

import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.command.transaction.CreateAssignmentCommand;
import com.dharbor.sintaxterrors.asset_service.command.transaction.CreateExchangeCommand;
import com.dharbor.sintaxterrors.asset_service.command.transaction.CreateReturnCommand;
import com.dharbor.sintaxterrors.asset_service.command.transaction.GetTransactionPageCommand;
import com.dharbor.sintaxterrors.asset_service.controller.constant.TransactionControllerConstants;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.AssignmentRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.ExchangeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.GetTransactionPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.ReturnRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonPaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.transaction.TransactionResponse;
import com.dharbor.sintaxterrors.asset_service.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.asset_service.enums.transaction.TransactionType;
import com.dharbor.sintaxterrors.asset_service.utils.constant.FilterConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;

@Tag(name = TransactionControllerConstants.TransactionSwaggerDoc.TAG_NAME,
        description = TransactionControllerConstants.TransactionSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = TransactionControllerConstants.TransactionPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final CreateAssignmentCommand createAssignmentCommand;
    private final CreateReturnCommand createReturnCommand;
    private final CreateExchangeCommand createExchangeCommand;
    private final GetTransactionPageCommand getTransactionPageCommand;

    @Operation(summary = TransactionControllerConstants.TransactionSwaggerDoc.TAG_CREATE_ASSIGNMENT)
    @PostMapping("/assignments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<TransactionResponse> createAssignment(
            @Valid @RequestBody AssignmentRequest request) {
        createAssignmentCommand.setInput(request);
        (new SafeCommandExecutor<AssignmentRequest, TransactionResponse>()).safeExecution(createAssignmentCommand);
        return new CommonResponse<>(
                createAssignmentCommand.getOutput(),
                HttpStatus.CREATED,
                "Assignment created successfully");
    }

    @Operation(summary = TransactionControllerConstants.TransactionSwaggerDoc.TAG_CREATE_RETURN)
    @PostMapping("/returns")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<TransactionResponse> createReturn(
            @Valid @RequestBody ReturnRequest request) {
        createReturnCommand.setInput(request);
        (new SafeCommandExecutor<ReturnRequest, TransactionResponse>()).safeExecution(createReturnCommand);
        return new CommonResponse<>(
                createReturnCommand.getOutput(),
                HttpStatus.CREATED,
                "Return processed successfully");
    }

    @Operation(summary = TransactionControllerConstants.TransactionSwaggerDoc.TAG_CREATE_EXCHANGE)
    @PostMapping("/exchanges")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<TransactionResponse> createExchange(
            @Valid @RequestBody ExchangeRequest request) {
        createExchangeCommand.setInput(request);
        (new SafeCommandExecutor<ExchangeRequest, TransactionResponse>()).safeExecution(createExchangeCommand);
        return new CommonResponse<>(
                createExchangeCommand.getOutput(),
                HttpStatus.CREATED,
                "Exchange completed successfully");
    }

    @Operation(summary = TransactionControllerConstants.TransactionSwaggerDoc.TAG_GET_TRANSACTION_BY_PAGE)
    @GetMapping
    public CommonPaginationResponse<TransactionResponse> getTransactionPage(
            @RequestParam(defaultValue = FilterConstant.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = FilterConstant.AMOUNT_MAX_BY_PAGE) Integer size,
            @RequestParam(required = false) String order,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_FILTER_DIRECTION) SortDirection direction,
            @RequestParam(required = false) TransactionType transactionType,
            @RequestParam(required = false) Long employeeId,
            @RequestParam(required = false) Long assetId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) String note) {

        GetTransactionPageRequest request = new GetTransactionPageRequest();
        request.setPage(page);
        request.setSize(size);
        request.setOrder(order);
        request.setDirection(direction);
        request.setTransactionType(transactionType);
        request.setEmployeeId(employeeId);
        request.setAssetId(assetId);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setNote(note);

        getTransactionPageCommand.setInput(request);
        (new SafeCommandExecutor<GetTransactionPageRequest, PaginationResponse<TransactionResponse>>())
                .safeExecution(getTransactionPageCommand);

        return new CommonPaginationResponse<>(
                getTransactionPageCommand.getOutput(),
                HttpStatus.OK,
                "Transactions retrieved successfully");
    }
}