package com.dharbor.sintaxterrors.controller.notification;


import com.dharbor.sintaxterrors.command.notification.GetNotificationByIdCommand;
import com.dharbor.sintaxterrors.command.notification.GetNotificationPageCommand;
import com.dharbor.sintaxterrors.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.controller.constant.NotificationControllerConstant.NotificationSwagger;
import com.dharbor.sintaxterrors.controller.constant.NotificationControllerConstant.NotificationPath;
import com.dharbor.sintaxterrors.controller.enums.NotificationOrderCriteria;
import com.dharbor.sintaxterrors.dto.CommonPaginationResponse;
import com.dharbor.sintaxterrors.dto.CommonResponse;
import com.dharbor.sintaxterrors.dto.PaginationResponse;
import com.dharbor.sintaxterrors.dto.request.GetNotificationPageRequest;
import com.dharbor.sintaxterrors.dto.response.NotificationResponse;
import com.dharbor.sintaxterrors.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.util.constant.FilterConstant;
import com.dharbor.sintaxterrors.util.function.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = NotificationSwagger.TAG_NAME, description = NotificationSwagger.TAG_DESCRIPTION)
@RequestMapping(value =  NotificationPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class GetNotificationController {

    private final GetNotificationByIdCommand getNotificationByIdCommand;
    private final GetNotificationPageCommand getNotificationPageCommand;

    @GetMapping("/{id}")
    @Operation(summary = NotificationSwagger.TAG_DESCRIPTION_ID)
    public CommonResponse<NotificationResponse> getNotificationById(@PathVariable Long id) {
        getNotificationByIdCommand.setInput(id);
        (new SafeCommandExecutor<Long, NotificationResponse>()).safeExecution(getNotificationByIdCommand);
        return new CommonResponse<>(getNotificationByIdCommand.getOutput());
    }

    @GetMapping()
    @Operation(summary = NotificationSwagger.TAG_DESCRIPTION_PAGE)
    public CommonPaginationResponse<NotificationResponse> getNotificationListByPage(
            @RequestParam(defaultValue = FilterConstant.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_SIZE) Integer size,
            @RequestParam(required = false) NotificationOrderCriteria order,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_FILTER_DIRECTION) SortDirection direction,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Boolean isRead) {
        GetNotificationPageRequest request = GetNotificationPageRequest.builder()
                .page(page)
                .size(size)
                .order(!Utils.isNull(order) ? order.getValue() : null)
                .direction(direction)
                .userId(userId)
                .isRead(isRead)
                .build();
        getNotificationPageCommand.setInput(request);
        (new SafeCommandExecutor<GetNotificationPageRequest, PaginationResponse<NotificationResponse>>()).safeExecution(getNotificationPageCommand);
        return new CommonPaginationResponse<>(getNotificationPageCommand.getOutput());
    }
}
