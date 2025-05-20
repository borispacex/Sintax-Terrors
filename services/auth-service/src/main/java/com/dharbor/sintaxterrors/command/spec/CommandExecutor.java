package com.dharbor.sintaxterrors.command.spec;

public interface CommandExecutor<I, O> {
    void preExecute(Command<I, O> instance);

    void execute(Command<I, O> instance);

    void postExecute(Command<I, O> instance);
}
