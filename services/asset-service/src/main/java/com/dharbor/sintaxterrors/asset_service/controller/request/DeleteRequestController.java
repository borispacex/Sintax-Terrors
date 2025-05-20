package com.dharbor.sintaxterrors.asset_service.controller.request;

import com.dharbor.sintaxterrors.asset_service.command.request.DeleteRequestCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.RequestControllerConstants.RequestPath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.RequestControllerConstants.RequestSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.request.RequestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = RequestSwaggerDoc.TAG_NAME, description = RequestSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = RequestPath.BASE_PATH)
@RestController
@RequestScope
@RequiredArgsConstructor
public class DeleteRequestController {
    private final DeleteRequestCommand deleteRequestCommand;

    @Operation(summary = RequestSwaggerDoc.TAG_DELETE_REQUEST)
    @DeleteMapping(value = "/{id}")
    public CommonResponse<RequestResponse> deleteRequest(@PathVariable Integer id) {
        deleteRequestCommand.setInput(id);
        (new SafeCommandExecutor<Integer, RequestResponse>()).safeExecution(deleteRequestCommand);
        return new CommonResponse<>(deleteRequestCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.toString());
    }
}
