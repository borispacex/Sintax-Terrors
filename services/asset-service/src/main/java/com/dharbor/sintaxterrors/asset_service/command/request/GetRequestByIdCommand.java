package com.dharbor.sintaxterrors.asset_service.command.request;


import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
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
public class GetRequestByIdCommand
        extends SafeAbstractCommand<Integer, RequestResponse>
        implements PostExecutorCommand {

    private final RequestService requestService;

    @Override
    public void execute() {
        log.info("GetTeamByIdCommand - Execute");
        this.output = requestService.findRequestResponseById(this.input);
    }

    @Override
    public void postExecute() {
        log.info("GetTeamByIdCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(RequestExceptionConstants.FAILED_TO_GET_REQUEST);
        }
    }
}