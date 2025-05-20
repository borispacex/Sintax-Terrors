package com.dharbor.sintaxterrors.command.spec;

import com.dharbor.sintaxterrors.exception.CommandException;
import com.dharbor.sintaxterrors.exception.ProcessErrorException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;



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
        }   catch (Exception exception) {
            throw new CommandException(exception);
        }
    }
}
