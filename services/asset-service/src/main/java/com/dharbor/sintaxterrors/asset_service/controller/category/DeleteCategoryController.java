package com.dharbor.sintaxterrors.asset_service.controller.category;

import com.dharbor.sintaxterrors.asset_service.command.category.DeleteCategoryCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.CategoryControllerConstants.CategoryPath;
import com.dharbor.sintaxterrors.asset_service.controller.constant.CategoryControllerConstants.CategorySwaggerDoc;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.category.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = CategorySwaggerDoc.TAG_NAME, description = CategorySwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = CategoryPath.BASE_PATH)
@RestController
@RequestScope
@RequiredArgsConstructor
public class DeleteCategoryController {
    private final DeleteCategoryCommand deleteCategoryCommand;

    @Operation(summary = CategorySwaggerDoc.TAG_DELETE_CATEGORY)
    @DeleteMapping(value = "/{id}")
    public CommonResponse<CategoryResponse> deleteCategory(@PathVariable Long id) {
        deleteCategoryCommand.setInput(id);
        (new SafeCommandExecutor<Long, CategoryResponse>()).safeExecution(deleteCategoryCommand);
        return new CommonResponse<>(deleteCategoryCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.toString());
    }
}
