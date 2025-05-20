package com.dharbor.sintaxterrors.asset_service.controller.asset;

import com.dharbor.sintaxterrors.asset_service.command.asset.GetAssetFinancialCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.AssetControllerConstants;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetFinancialResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = AssetControllerConstants.AssetSwaggerDoc.TAG_NAME,
        description = AssetControllerConstants.AssetSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = AssetControllerConstants.AssetPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class FinancialAssetController {

    private final GetAssetFinancialCommand getAssetFinancialCommand;

    @Operation(summary = AssetControllerConstants.AssetSwaggerDoc.TAG_GET_FINANCIAL_INFO)
    @GetMapping(AssetControllerConstants.AssetPath.FINANCIAL_INFO)
    public CommonResponse<AssetFinancialResponse> getAssetFinancialInfo(
            @PathVariable Long id) {
        getAssetFinancialCommand.setInput(id);
        (new SafeCommandExecutor<Long, AssetFinancialResponse>()).safeExecution(getAssetFinancialCommand);
        return new CommonResponse<AssetFinancialResponse>(
                getAssetFinancialCommand.getOutput(),
                HttpStatus.OK,
                HttpStatus.OK.name());
    }
}
