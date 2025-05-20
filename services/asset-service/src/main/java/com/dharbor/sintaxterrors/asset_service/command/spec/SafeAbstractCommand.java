package com.dharbor.sintaxterrors.asset_service.command.spec;



import com.dharbor.sintaxterrors.asset_service.exception.CommandException;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import jakarta.validation.ConstraintViolationException;

public abstract class SafeAbstractCommand<I, O> extends Command<I, O> {
    public void safeExecute() throws CommandException {
        try {
            this.execute();
        } catch (
                ProcessErrorException |
                ConstraintViolationException |
                IllegalArgumentException |
                HttpMessageNotReadableException exception
        ) {
            throw exception;
        } catch (Exception exception) {
            throw new CommandException(exception);
        }
    }
}
