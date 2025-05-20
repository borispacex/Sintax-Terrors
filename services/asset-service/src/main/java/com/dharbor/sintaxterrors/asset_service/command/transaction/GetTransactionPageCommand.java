package com.dharbor.sintaxterrors.asset_service.command.transaction;

import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.request.transaction.GetTransactionPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
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
public class GetTransactionPageCommand
        extends SafeAbstractCommand<GetTransactionPageRequest, PaginationResponse<TransactionResponse>>
        implements PostExecutorCommand {

    private final TransactionService transactionService;

    @Override
    public void execute() {
        log.info("GetTransactionPageCommand - Execute");
        this.output = transactionService.getTransactionPage(this.input);
    }

    @Override
    public void postExecute() {
        log.info("GetTransactionPageCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(TransactionExceptionConstants.FAILED_TO_GET_TRANSACTION_PAGE);
        }
    }
}