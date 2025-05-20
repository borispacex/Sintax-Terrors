package com.dharbor.sintaxterrors.command.user;

import com.dharbor.sintaxterrors.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.dto.response.user.UserResponse;
import com.dharbor.sintaxterrors.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.exception.constant.UserExceptionConstant;
import com.dharbor.sintaxterrors.service.UserService;
import com.dharbor.sintaxterrors.util.constant.ScopeConstant;
import com.dharbor.sintaxterrors.util.function.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class GetUserByIdCommand
        extends SafeAbstractCommand<Integer, UserResponse>
        implements PostExecutorCommand {

    private final UserService userService;

    @Override
    public void execute() {
        log.info("GetUserByIdCommand - Execute");
        this.output = this.userService.getUserById(this.input);
    }

    @Override
    public void postExecute() {
        log.info("UserGetByIdCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(UserExceptionConstant.FAILED_TO_GET_USER_MESSAGE);
        }
    }

}
