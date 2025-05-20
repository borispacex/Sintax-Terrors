package com.dharbor.sintaxterrors.asset_service.command.category;


import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
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
public class GetCategoryByIdCommand
        extends SafeAbstractCommand<Long, CategoryResponse>
        implements PostExecutorCommand {
    private final CategoryService categoryService;

    @Override
    public void execute() {
        log.info("GetCategoryByIdCommand - Execute");
        this.output = categoryService.findCategoryById(this.input);
    }

    @Override
    public void postExecute() {
        log.info("GetCategoryByIdCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(CategoryExceptionConstants.FAILED_TO_GET_CATEGORY);
        }
    }
}