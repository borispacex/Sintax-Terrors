package com.dharbor.sintaxterrors.asset_service.command.category;


import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.response.category.CategoryResponse;
import com.dharbor.sintaxterrors.asset_service.service.CategoryService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class DeleteCategoryCommand extends
        SafeAbstractCommand<Long, CategoryResponse> {
    private final CategoryService categoryService;

    @Override
    public void execute() {
        log.info("DeleteCategoryCommand - Execute");
        this.output = categoryService.deleteCategory(this.input);
    }

}