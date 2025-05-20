package com.dharbor.sintaxterrors.asset_service.command.transaction;

import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.ExchangeRequest;
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
public class CreateExchangeCommand
        extends SafeAbstractCommand<ExchangeRequest, TransactionResponse>
        implements PreExecutorCommand, PostExecutorCommand {

    private final TransactionService transactionService;

    @Override
    public void preExecute() {
        log.info("CreateExchangeCommand - PreExecute");
        transactionService.validateExchangeRequest(input);
    }

    @Override
    public void execute() {
        log.info("CreateExchangeCommand - Execute");
        this.output = transactionService.createExchange(this.input);
    }

    @Override
    public void postExecute() {
        log.info("CreateExchangeCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(TransactionExceptionConstants.FAILED_TO_CREATE_EXCHANGE);
        }
    }
}
