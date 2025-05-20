package com.dharbor.sintaxterrors.asset_service.command.request;

import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.request.request.CreateRequestRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.request.RequestResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.RequestExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.service.RequestService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class CreateRequestCommand extends
        SafeAbstractCommand<CreateRequestRequest, RequestResponse>
        implements PreExecutorCommand, PostExecutorCommand {

    private final RequestService requestService;

    @Override
    public void preExecute() {
        log.info("CreateRequestCommand - PreExecute");
        requestService.validateCreateRequestRequest(this.input);
    }

    @Override
    public void execute() {
        log.info("CreateRequestCommand - Execute");
        this.output = requestService.saveRequest(this.input);
    }

    @Override
    public void postExecute() {
        log.info("CreateRequestCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(RequestExceptionConstants.FAILED_TO_CREATE_REQUEST);
        }
    }
}