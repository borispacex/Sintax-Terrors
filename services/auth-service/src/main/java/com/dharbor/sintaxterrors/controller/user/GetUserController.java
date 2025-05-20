package com.dharbor.sintaxterrors.controller.user;

import com.dharbor.sintaxterrors.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.command.user.GetUserByIdCommand;
import com.dharbor.sintaxterrors.command.user.GetUserByTokenCommand;
import com.dharbor.sintaxterrors.command.user.GetUserPageCommand;
import com.dharbor.sintaxterrors.controller.constant.AppControllerConstant;
import com.dharbor.sintaxterrors.controller.constant.AuthControllerConstant;
import com.dharbor.sintaxterrors.controller.constant.UserControllerConstant.UserPath;
import com.dharbor.sintaxterrors.controller.constant.UserControllerConstant.UserSwagger;
import com.dharbor.sintaxterrors.controller.enums.UserOrderCriteria;
import com.dharbor.sintaxterrors.dto.CommonPaginationResponse;
import com.dharbor.sintaxterrors.dto.CommonResponse;
import com.dharbor.sintaxterrors.dto.PaginationResponse;
import com.dharbor.sintaxterrors.dto.request.auth.MeResponse;
import com.dharbor.sintaxterrors.dto.request.user.GetUserPageRequest;
import com.dharbor.sintaxterrors.dto.response.user.UserEmployeeResponse;
import com.dharbor.sintaxterrors.dto.response.user.UserResponse;
import com.dharbor.sintaxterrors.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.model.entity.user.enums.UserState;
import com.dharbor.sintaxterrors.util.constant.FilterConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDate;

@Tag(name = UserSwagger.TAG_NAME, description = UserSwagger.TAG_DESCRIPTION)
@RequestMapping(value = AppControllerConstant.AppPath.BASE_PRIVATE_PATH + UserPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class GetUserController {

    private final GetUserByIdCommand getUserByIdCommand;
    private final GetUserPageCommand getUserPageCommand;
    private final GetUserByTokenCommand getUserByTokenCommand;


    @GetMapping("/me")
    @Operation(summary = UserSwagger.TAG_DESCRIPTION_ME)
    public CommonResponse<UserEmployeeResponse> me(@RequestHeader("Authorization") String authHeader) {
        getUserByTokenCommand.setInput(authHeader);
        (new SafeCommandExecutor<String, UserEmployeeResponse>()).safeExecution(getUserByTokenCommand);
        return new CommonResponse<>(getUserByTokenCommand.getOutput());
    }

    @GetMapping("/{id}")
    @Operation(summary = UserSwagger.TAG_DESCRIPTION_ID)
    public CommonResponse<UserResponse> getUserById(
            @PathVariable Integer id
    ) {
        getUserByIdCommand.setInput(id);
        (new SafeCommandExecutor<Integer, UserResponse>()).safeExecution(getUserByIdCommand);
        return new CommonResponse<>(getUserByIdCommand.getOutput());
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping()
    @Operation(summary = UserSwagger.TAG_DESCRIPTION_PAGE)
    public CommonPaginationResponse<UserResponse> getUserListByPage(
            @RequestParam(defaultValue = FilterConstant.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_SIZE) Integer size,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_FILTER_DIRECTION) SortDirection direction,
            @RequestParam(required = false) UserOrderCriteria order,
            @RequestParam(required = false) String username,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateJoined,
            @RequestParam(required = false) UserState state,
            @RequestParam(required = false) Integer roleId
    ) {
        GetUserPageRequest request = GetUserPageRequest.builder()
                .page(page)
                .size(size)
                .direction(direction)
                .order(order)
                .username(username)
                .dateJoined(dateJoined)
                .state(state)
                .roleId(roleId)
                .build();

        getUserPageCommand.setInput(request);
        (new SafeCommandExecutor<GetUserPageRequest, PaginationResponse<UserResponse>>()).safeExecution(getUserPageCommand);
        return new CommonPaginationResponse<>(getUserPageCommand.getOutput());
    }
}
