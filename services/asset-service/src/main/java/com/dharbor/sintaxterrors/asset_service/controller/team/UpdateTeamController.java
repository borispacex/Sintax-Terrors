package com.dharbor.sintaxterrors.asset_service.controller.team;

import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.command.team.UpdateTeamCommand;
import com.dharbor.sintaxterrors.asset_service.controller.constant.TeamControllerConstants.TeamPath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.TeamControllerConstants.TeamSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.dto.request.team.UpdateTeamRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.team.TeamResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = TeamSwaggerDoc.TAG_NAME, description = TeamSwaggerDoc.TAG_DESCRIPTION)
@RestController
@RequestScope
@RequestMapping(value = TeamPath.BASE_PATH)
@RequiredArgsConstructor
public class UpdateTeamController {
    private final UpdateTeamCommand updateTeamCommand;

    @Operation(summary = TeamSwaggerDoc.TAG_UPDATE_TEAM)
    @PutMapping()
    public CommonResponse<TeamResponse> updateTeam(@Valid @RequestBody UpdateTeamRequest request) {
        updateTeamCommand.setInput(request);
        (new SafeCommandExecutor<UpdateTeamRequest, TeamResponse>()).safeExecution(updateTeamCommand);
        return new CommonResponse<>(updateTeamCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.toString());
    }

}
