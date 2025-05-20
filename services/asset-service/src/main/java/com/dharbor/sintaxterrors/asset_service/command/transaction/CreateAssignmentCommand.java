package com.dharbor.sintaxterrors.asset_service.command.transaction;

import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.AssignmentRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.transaction.TransactionResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.TransactionExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.service.TransactionService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class CreateAssignmentCommand
        extends SafeAbstractCommand<AssignmentRequest, TransactionResponse>
        implements PreExecutorCommand, PostExecutorCommand {

    private final TransactionService transactionService;

    @Override
    public void preExecute() {
        log.info("CreateAssignmentCommand - PreExecute");
        transactionService.validateAssignmentRequest(input);
    }

    @Override
    public void execute() {
        log.info("CreateAssignmentCommand - Execute");
        this.output = transactionService.createAssignment(this.input);
    }

    @Override
    public void postExecute() {
        log.info("CreateAssignmentCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(TransactionExceptionConstants.FAILED_TO_CREATE_ASSIGNMENT);
        }
    }
}
