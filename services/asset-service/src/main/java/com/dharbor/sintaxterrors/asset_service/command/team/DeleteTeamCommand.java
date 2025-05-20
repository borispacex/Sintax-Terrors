package com.dharbor.sintaxterrors.asset_service.command.team;


import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.response.team.TeamResponse;
import com.dharbor.sintaxterrors.asset_service.service.TeamService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class DeleteTeamCommand extends
        SafeAbstractCommand<Integer, TeamResponse> {

    private final TeamService teamService;

    @Override
    public void execute() {
        log.info("DeleteTeamCommand - Execute");
        this.output = teamService.deleteTeam(this.input);
    }
}