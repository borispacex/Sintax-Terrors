package com.dharbor.sintaxterrors.command.spec;


import com.dharbor.sintaxterrors.exception.CommandException;

public class SafeCommandExecutor<I, O> implements CommandExecutor<I, O> {
    @Override
    public void preExecute(Command<I, O> instance) {
        if (instance instanceof PreExecutorCommand) {
            ((PreExecutorCommand) instance).preExecute();
        }
    }

    @Override
    public void execute(Command<I, O> instance) {
    }

    public void safeExecution(Command<I, O> instance) throws CommandException {
        this.preExecute(instance);
        ((SafeAbstractCommand<I, O>) instance).safeExecute();
        this.postExecute(instance);
    }

    @Override
    public void postExecute(Command<I, O> instance) {
        if (instance instanceof PostExecutorCommand) {
            ((PostExecutorCommand) instance).postExecute();
        }
    }
}
