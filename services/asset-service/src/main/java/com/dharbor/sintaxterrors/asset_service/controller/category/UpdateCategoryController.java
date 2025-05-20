package com.dharbor.sintaxterrors.asset_service.controller.category;

import com.dharbor.sintaxterrors.asset_service.command.category.UpdateCategoryCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.CategoryControllerConstants.CategoryPath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.CategoryControllerConstants.CategorySwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.dto.request.category.UpdateCategoryRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.category.CategoryResponse;
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

@Tag(name = CategorySwaggerDoc.TAG_NAME, description = CategorySwaggerDoc.TAG_DESCRIPTION)
@RestController
@RequestScope
@RequestMapping(value = CategoryPath.BASE_PATH)
@RequiredArgsConstructor
public class UpdateCategoryController {
    private final UpdateCategoryCommand updateCategoryCommand;

    @Operation(summary = CategorySwaggerDoc.TAG_UPDATE_CATEGORY)
    @PutMapping()
    public CommonResponse<CategoryResponse> updateCategory(@Valid @RequestBody UpdateCategoryRequest request) {
        updateCategoryCommand.setInput(request);
        (new SafeCommandExecutor<UpdateCategoryRequest, CategoryResponse>()).safeExecution(updateCategoryCommand);
        return new CommonResponse<>(updateCategoryCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.toString());
    }

}
