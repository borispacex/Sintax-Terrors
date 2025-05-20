package com.dharbor.sintaxterrors.asset_service.controller.category;

import com.dharbor.sintaxterrors.asset_service.command.category.CreateCategoryCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.CategoryControllerConstants.CategorySwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.controller.constant.CategoryControllerConstants.CategoryPath;
import com.dharbor.sintaxterrors.asset_service.dto.request.category.CreateCategoryRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.category.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = CategorySwaggerDoc.TAG_NAME, description = CategorySwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = CategoryPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class CreateCategoryController {
    private final CreateCategoryCommand createCategoryCommand;

    @Operation(summary = CategorySwaggerDoc.TAG_CREATE_CATEGORY)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        createCategoryCommand.setInput(request);
        (new SafeCommandExecutor<CreateCategoryRequest, CategoryResponse>()).safeExecution(createCategoryCommand);
        return new CommonResponse<>(createCategoryCommand.getOutput(), HttpStatus.CREATED, HttpStatus.CREATED.name());
    }
}
