package com.dharbor.sintaxterrors.asset_service.command.category;

import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.request.category.CreateCategoryRequest;
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
public class CreateCategoryCommand extends
        SafeAbstractCommand<CreateCategoryRequest, CategoryResponse>
        implements PreExecutorCommand, PostExecutorCommand {
    private final CategoryService categoryService;

    @Override
    public void preExecute() {
        log.info("CreateCategoryCommand - PreExecute");
        categoryService.validateCreateCategoryRequest(this.input);
    }

    @Override
    public void execute() {
        log.info("CreateCategoryCommand - Execute");
        this.output = categoryService.saveCategory(this.input);
    }

    @Override
    public void postExecute() {
        log.info("CreateCategoryCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(CategoryExceptionConstants.FAILED_TO_CREATE_CATEGORY);
        }
    }
}