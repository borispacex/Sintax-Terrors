package com.dharbor.sintaxterrors.asset_service.command.team;


import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.request.team.CreateTeamRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.team.TeamResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.TeamExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.service.TeamService;
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
public class CreateTeamCommand extends
        SafeAbstractCommand<CreateTeamRequest, TeamResponse>
        implements PreExecutorCommand, PostExecutorCommand {

    private final TeamService teamService;

    @Override
    public void preExecute() {
        log.info("CreateTeamCommand - PreExecute");
        teamService.validateCreateTeamRequest(this.input);
    }

    @Override
    public void execute() {
        log.info("CreateTeamCommand - Execute");
        this.output = teamService.saveTeam(this.input);
    }

    @Override
    public void postExecute() {
        log.info("CreateTeamCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(TeamExceptionConstants.FAILED_TO_CREATE_TEAM);
        }
    }
}