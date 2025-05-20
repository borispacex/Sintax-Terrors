package com.dharbor.sintaxterrors.asset_service.controller.team;

import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.command.team.DeleteTeamCommand;
import com.dharbor.sintaxterrors.asset_service.controller.constant.TeamControllerConstants.TeamPath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.TeamControllerConstants.TeamSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.team.TeamResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = TeamSwaggerDoc.TAG_NAME, description = TeamSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = TeamPath.BASE_PATH)
@RestController
@RequestScope
@RequiredArgsConstructor
public class DeleteTeamController {
    private final DeleteTeamCommand deleteTeamCommand;

    @Operation(summary = TeamSwaggerDoc.TAG_DELETE_TEAM)
    @DeleteMapping(value = "/{id}")
    public CommonResponse<TeamResponse> deleteTeam(@PathVariable Integer id) {
        deleteTeamCommand.setInput(id);
        (new SafeCommandExecutor<Integer, TeamResponse>()).safeExecution(deleteTeamCommand);
        return new CommonResponse<>(deleteTeamCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.toString());
    }
}
