package com.dharbor.sintaxterrors.asset_service.command.spec;

public interface CommandExecutor<I, O> {
    void preExecute(Command<I, O> instance);

    void execute(Command<I, O> instance);

    void postExecute(Command<I, O> instance);
}
