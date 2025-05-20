package com.dharbor.sintaxterrors.asset_service.command.transaction;

import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
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

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class GetTransactionByIdCommand
        extends SafeAbstractCommand<Long, TransactionResponse>
        implements PreExecutorCommand, PostExecutorCommand {

    private final TransactionService transactionService;

    @Override
    public void preExecute() {
        log.info("GetTransactionByIdCommand - PreExecute");
        if (!transactionService.existsById(this.input)) {
            throw new ProcessErrorException(TransactionExceptionConstants.TRANSACTION_NOT_FOUND);
        }
    }

    @Override
    public void execute() {
        log.info("GetTransactionByIdCommand - Execute");
        this.output = transactionService.getTransactionById(this.input);
    }

    @Override
    public void postExecute() {
        log.info("GetTransactionByIdCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(TransactionExceptionConstants.FAILED_TO_GET_TRANSACTION);
        }
    }
}
