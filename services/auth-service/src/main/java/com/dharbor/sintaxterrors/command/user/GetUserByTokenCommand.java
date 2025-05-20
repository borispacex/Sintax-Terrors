package com.dharbor.sintaxterrors.command.user;

import com.dharbor.sintaxterrors.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.dto.response.user.UserEmployeeResponse;
import com.dharbor.sintaxterrors.dto.response.user.UserResponse;
import com.dharbor.sintaxterrors.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.exception.constant.UserExceptionConstant;
import com.dharbor.sintaxterrors.security.JwtUtil;
import com.dharbor.sintaxterrors.service.UserService;
import com.dharbor.sintaxterrors.util.constant.ScopeConstant;
import com.dharbor.sintaxterrors.util.function.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class GetUserByTokenCommand
        extends SafeAbstractCommand<String, UserEmployeeResponse>
        implements PostExecutorCommand, PreExecutorCommand {

    private final UserService userService;


    @Override
    public void preExecute() {
        log.info("GetUserByTokenCommand - PreExecute");
        if (Utils.isNull(this.input)) {
            throw new ProcessErrorException(UserExceptionConstant.FAILED_TO_GET_USER_MESSAGE);
        }
        this.input = this.userService.validateAuthorization(this.input);
    }

    @Override
    public void execute() {
        log.info("GetUserByTokenCommand - Execute");
        this.output = this.userService.getUserByUsername(this.input);
    }

    @Override
    public void postExecute() {
        log.info("GetUserByTokenCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(UserExceptionConstant.FAILED_TO_GET_USER_MESSAGE);
        }
    }

}
