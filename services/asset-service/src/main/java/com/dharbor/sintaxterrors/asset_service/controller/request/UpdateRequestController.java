package com.dharbor.sintaxterrors.asset_service.controller.request;

import com.dharbor.sintaxterrors.asset_service.command.request.UpdateRequestCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.RequestControllerConstants.RequestPath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.RequestControllerConstants.RequestSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.dto.request.request.UpdateRequestRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.request.RequestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = RequestSwaggerDoc.TAG_NAME, description = RequestSwaggerDoc.TAG_DESCRIPTION)
@RestController
@RequestScope
@RequestMapping(value = RequestPath.BASE_PATH)
@RequiredArgsConstructor
public class UpdateRequestController {
    private final UpdateRequestCommand updateRequestCommand;

    @Operation(summary = RequestSwaggerDoc.TAG_UPDATE_REQUEST)
    @PutMapping()
    public CommonResponse<RequestResponse> updateTeam(@Valid @RequestBody UpdateRequestRequest request) {
        updateRequestCommand.setInput(request);
        (new SafeCommandExecutor<UpdateRequestRequest, RequestResponse>()).safeExecution(updateRequestCommand);
        return new CommonResponse<>(updateRequestCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.toString());
    }

}
