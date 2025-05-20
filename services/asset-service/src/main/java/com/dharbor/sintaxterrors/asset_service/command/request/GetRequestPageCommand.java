package com.dharbor.sintaxterrors.asset_service.command.request;


import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.request.request.GetRequestPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
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
public class GetRequestPageCommand
        extends SafeAbstractCommand<GetRequestPageRequest, PaginationResponse<RequestResponse>>
        implements PostExecutorCommand {

    private final RequestService requestService;

    @Override
    public void execute() {
        log.info("GetRequestsPageCommand - Execute");
        this.output = requestService.getRequestPage(this.input);
    }

    @Override
    public void postExecute() {
        log.info("GetRequestsPageCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(RequestExceptionConstants.FAILED_TO_GET_REQUEST_PAGE);
        }
    }
}