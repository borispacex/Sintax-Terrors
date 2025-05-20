package com.dharbor.sintaxterrors.asset_service.controller.employee;


import com.dharbor.sintaxterrors.asset_service.command.employee.*;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.EmployeeControllerConstants.EmployeePath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.EmployeeControllerConstants.EmployeeSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.controller.enums.EmployeeOrderCriteria;
import com.dharbor.sintaxterrors.asset_service.dto.request.employee.GetEmployeePageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonPaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeShortResponse;
import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.enums.employee.EmployeeStatus;
import com.dharbor.sintaxterrors.asset_service.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.asset_service.utils.constant.FilterConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Tag(name = EmployeeSwaggerDoc.TAG_NAME, description = EmployeeSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = EmployeePath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class GetEmployeeController {

    private final GetEmployeeByIdCommand getEmployeeByIdCommand;
    private final GetEmployeeByUserIdCommand getEmployeeByUserIdCommand;
    private final GetEmployeePageCommand getEmployeePageCommand;
    private final GetEmployeeWithTransactionPageCommand getEmployeeWithTransactionPageCommand;

    private final GetEmployeesByIdListCommand getEmployeesByIdListCommand;

    @Operation(summary = EmployeeSwaggerDoc.TAG_GET_EMPLOYEE_BY_ID)
    @GetMapping(value = "/{id}")
    public CommonResponse<EmployeeResponse> getEmployeeById(@PathVariable Integer id) {
        getEmployeeByIdCommand.setInput(id);
        (new SafeCommandExecutor<Integer, EmployeeResponse>()).safeExecution(getEmployeeByIdCommand);
        return new CommonResponse<>(getEmployeeByIdCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }

    @Operation(summary = EmployeeSwaggerDoc.TAG_GET_EMPLOYEE_BY_ID)
    @GetMapping(value = "/user/{id}")
    public CommonResponse<EmployeeShortResponse> getEmployeeByUserId(@PathVariable Integer id) {
        getEmployeeByUserIdCommand.setInput(id);
        (new SafeCommandExecutor<Integer, EmployeeShortResponse>()).safeExecution(getEmployeeByUserIdCommand);
        return new CommonResponse<>(getEmployeeByUserIdCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }

    @GetMapping
    @Operation(summary = EmployeeSwaggerDoc.TAG_GET_EMPLOYEES_BY_PAGE)
    public CommonPaginationResponse<EmployeeResponse> getEmployeePage(
            @RequestParam(defaultValue = FilterConstant.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = FilterConstant.AMOUNT_MAX_BY_PAGE) Integer size,
            @RequestParam(required = false) EmployeeOrderCriteria order,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_FILTER_DIRECTION) SortDirection direction,
            @RequestParam(required = false) EmployeeStatus status,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) BoliviaCity city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer teamId
    ) {
        GetEmployeePageRequest request = new GetEmployeePageRequest(
                page,
                size,
                (!Utils.isNull(order)) ? order.getValue() : null,
                direction,
                status,
                search,
                country,
                city,
                teamId
        );
        getEmployeePageCommand.setInput(request);
        (new SafeCommandExecutor<GetEmployeePageRequest, PaginationResponse<EmployeeResponse>>()).safeExecution(getEmployeePageCommand);
        return new CommonPaginationResponse<>(getEmployeePageCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }

    @GetMapping(value = "/withTransaction")
    @Operation(summary = EmployeeSwaggerDoc.TAG_GET_EMPLOYEES_WITH_TRANSACTION_PAGE)
    public CommonPaginationResponse<EmployeeResponse> getEmployeeWithTransactionPage(
            @RequestParam(defaultValue = FilterConstant.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = FilterConstant.AMOUNT_MAX_BY_PAGE) Integer size,
            @RequestParam(required = false) EmployeeOrderCriteria order,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_FILTER_DIRECTION) SortDirection direction,
            @RequestParam(required = false) EmployeeStatus status,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) BoliviaCity city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer teamId
    ) {
        GetEmployeePageRequest request = new GetEmployeePageRequest(
                page,
                size,
                (!Utils.isNull(order)) ? order.getValue() : null,
                direction,
                status,
                search,
                country,
                city,
                teamId
        );
        getEmployeeWithTransactionPageCommand.setInput(request);
        (new SafeCommandExecutor<GetEmployeePageRequest, PaginationResponse<EmployeeResponse>>()).safeExecution(getEmployeeWithTransactionPageCommand);
        return new CommonPaginationResponse<>(getEmployeeWithTransactionPageCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }

    @GetMapping(EmployeePath.BY_ID_LIST)
    @Operation(summary = EmployeeSwaggerDoc.TAG_GET_EMPLOYEES_BY_LIST)
    public CommonResponse<List<EmployeeResponse>> getEmployeesByIdList(@RequestBody List<Integer> idList) {
        getEmployeesByIdListCommand.setInput(idList);
        (new SafeCommandExecutor<List<Integer>, List<EmployeeResponse>>()).safeExecution(getEmployeesByIdListCommand);
        return new CommonResponse<>(getEmployeesByIdListCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }
}