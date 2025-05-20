package com.dharbor.sintaxterrors.asset_service.command.spec;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public abstract class Command<I, O> {
    protected I input;
    protected O output;

    public void setInput(@Valid I input) {
        this.input = input;
    }

    public O getOutput() {
        return this.output;
    }

    protected abstract void execute();
}
