package com.dharbor.sintaxterrors.asset_service.controller.team;

import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.command.team.CreateTeamCommand;
import com.dharbor.sintaxterrors.asset_service.controller.constant.TeamControllerConstants.TeamPath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.TeamControllerConstants.TeamSwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.dto.request.team.CreateTeamRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.team.TeamResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = TeamSwaggerDoc.TAG_NAME, description = TeamSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = TeamPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class CreateTeamController {
    private final CreateTeamCommand createTeamCommand;

    @Operation(summary = TeamSwaggerDoc.TAG_CREATE_TEAM)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<TeamResponse> createTeam(@Valid @RequestBody CreateTeamRequest request) {
        createTeamCommand.setInput(request);
        (new SafeCommandExecutor<CreateTeamRequest, TeamResponse>()).safeExecution(createTeamCommand);
        return new CommonResponse<>(createTeamCommand.getOutput(), HttpStatus.CREATED, HttpStatus.CREATED.name());
    }
}
