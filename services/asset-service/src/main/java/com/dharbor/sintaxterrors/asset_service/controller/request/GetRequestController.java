package com.dharbor.sintaxterrors.asset_service.controller.request;

import com.dharbor.sintaxterrors.asset_service.command.request.GetRequestByIdCommand;
import com.dharbor.sintaxterrors.asset_service.command.request.GetRequestPageCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.RequestControllerConstants.RequestPath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.RequestControllerConstants.RequestSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.controller.enums.RequestOrderCriteria;
import com.dharbor.sintaxterrors.asset_service.dto.request.request.GetRequestPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonPaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.request.RequestResponse;
import com.dharbor.sintaxterrors.asset_service.enums.request.RequestType;
import com.dharbor.sintaxterrors.asset_service.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.asset_service.enums.shared.Status;
import com.dharbor.sintaxterrors.asset_service.utils.constant.FilterConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDate;

@Tag(name = RequestSwaggerDoc.TAG_NAME, description = RequestSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = RequestPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class GetRequestController {

    private final GetRequestByIdCommand getRequestByIdCommand;
    private final GetRequestPageCommand getRequestPageCommand;

    @GetMapping(value = "/{id}")
    @Operation(summary = RequestSwaggerDoc.TAG_GET_REQUEST_BY_ID)
    public CommonResponse<RequestResponse> getRequestById(@PathVariable Integer id) {
        getRequestByIdCommand.setInput(id);
        (new SafeCommandExecutor<Integer, RequestResponse>()).safeExecution(getRequestByIdCommand);
        return new CommonResponse<>(getRequestByIdCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.toString());
    }

    @GetMapping()
    @Operation(summary = RequestSwaggerDoc.TAG_GET_REQUESTS_BY_PAGE)
    public CommonPaginationResponse<RequestResponse> getRequestPage(
            @RequestParam(defaultValue = FilterConstant.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = FilterConstant.AMOUNT_MAX_BY_PAGE) Integer size,
            @RequestParam(required = false) RequestOrderCriteria order,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_FILTER_DIRECTION) SortDirection direction,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) RequestType type,
            @RequestParam(required = false) LocalDate createdAt,
            @RequestParam(required = false) Integer employeeId) {
        GetRequestPageRequest request = new GetRequestPageRequest(
                page,
                size,
                (!Utils.isNull(order)) ? order.getValue() : null,
                direction,
                title,
                status,
                type,
                createdAt,
                employeeId);
        getRequestPageCommand.setInput(request);
        (new SafeCommandExecutor<GetRequestPageRequest, PaginationResponse<RequestResponse>>()).safeExecution(getRequestPageCommand);
        return new CommonPaginationResponse<>(getRequestPageCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }
}
