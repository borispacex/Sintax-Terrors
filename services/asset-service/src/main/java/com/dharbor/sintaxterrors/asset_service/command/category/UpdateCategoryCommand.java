package com.dharbor.sintaxterrors.asset_service.command.category;

import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.request.category.UpdateCategoryRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.category.CategoryResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.CategoryExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.service.CategoryService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class UpdateCategoryCommand
        extends SafeAbstractCommand<UpdateCategoryRequest, CategoryResponse>
        implements PreExecutorCommand, PostExecutorCommand {
    private CategoryService categoryService;

    @Override
    public void preExecute() {
        log.info("UpdateCategoryCommand - PreExecute");
        categoryService.validateUpdateCategoryRequest(this.input);
    }

    @Override
    public void execute() {
        log.info("UpdateCategoryCommand - Execute");
        this.output = categoryService.updateCategory(this.input);
    }

    @Override
    public void postExecute() {
        log.info("UpdateCategoryCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(CategoryExceptionConstants.FAILED_TO_UPDATE_CATEGORY);
        }
    }
}
