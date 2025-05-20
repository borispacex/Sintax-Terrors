package com.dharbor.sintaxterrors.asset_service.command.request;


import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.response.request.RequestResponse;
import com.dharbor.sintaxterrors.asset_service.service.RequestService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class DeleteRequestCommand extends
        SafeAbstractCommand<Integer, RequestResponse> {

    private final RequestService requestService;

    @Override
    public void execute() {
        log.info("DeleteRequestCommand - Execute");
        this.output = requestService.deleteRequest(this.input);
    }

}