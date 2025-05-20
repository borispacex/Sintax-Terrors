package com.dharbor.sintaxterrors.asset_service.controller.category;

import com.dharbor.sintaxterrors.asset_service.command.category.GetCategoryByIdCommand;
import com.dharbor.sintaxterrors.asset_service.command.category.GetCategoryPageCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.CategoryControllerConstants.CategoryPath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.CategoryControllerConstants.CategorySwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.controller.enums.TeamOrderCriteria;
import com.dharbor.sintaxterrors.asset_service.dto.request.category.GetCategoryPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonPaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.category.CategoryResponse;
import com.dharbor.sintaxterrors.asset_service.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.asset_service.utils.constant.FilterConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = CategorySwaggerDoc.TAG_NAME, description = CategorySwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = CategoryPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class GetCategoryController {

    private final GetCategoryByIdCommand getCategoryByIdCommand;
    private final GetCategoryPageCommand getCategoryPageCommand;

    @GetMapping(value = "/{id}")
    @Operation(summary = CategorySwaggerDoc.TAG_GET_CATEGORY_BY_ID)
    public CommonResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {
        getCategoryByIdCommand.setInput(id);
        (new SafeCommandExecutor<Long, CategoryResponse>()).safeExecution(getCategoryByIdCommand);
        return new CommonResponse<>(getCategoryByIdCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.toString());
    }

    @GetMapping()
    @Operation(summary = CategorySwaggerDoc.TAG_GET_CATEGORIES_BY_PAGE)
    public CommonPaginationResponse<CategoryResponse> getCategoryPage(
            @RequestParam(defaultValue = FilterConstant.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = FilterConstant.AMOUNT_MAX_BY_PAGE) Integer size,
            @RequestParam(required = false) TeamOrderCriteria order,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_FILTER_DIRECTION) SortDirection direction,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean isDepreciable) {
        GetCategoryPageRequest request = new GetCategoryPageRequest(
                page,
                size,
                (!Utils.isNull(order)) ? order.getValue() : null,
                direction,
                name,
                isDepreciable);
        getCategoryPageCommand.setInput(request);
        (new SafeCommandExecutor<GetCategoryPageRequest, PaginationResponse<CategoryResponse>>()).safeExecution(getCategoryPageCommand);
        return new CommonPaginationResponse<>(getCategoryPageCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }
}
