package com.dharbor.sintaxterrors.asset_service.controller.request;

import com.dharbor.sintaxterrors.asset_service.command.request.CreateRequestCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.RequestControllerConstants.RequestPath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.RequestControllerConstants.RequestSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.dto.request.request.CreateRequestRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.request.RequestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = RequestSwaggerDoc.TAG_NAME, description = RequestSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = RequestPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class CreateRequestController {
    private final CreateRequestCommand createRequestCommand;

    @Operation(summary = RequestSwaggerDoc.TAG_CREATE_REQUEST)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<RequestResponse> createRequest(@Valid @RequestBody CreateRequestRequest request) {
        createRequestCommand.setInput(request);
        (new SafeCommandExecutor<CreateRequestRequest, RequestResponse>()).safeExecution(createRequestCommand);
        return new CommonResponse<>(createRequestCommand.getOutput(), HttpStatus.CREATED, HttpStatus.CREATED.name());
    }
}
