package com.dharbor.sintaxterrors.asset_service.controller.asset;

import com.dharbor.sintaxterrors.asset_service.command.asset.UpdateAssetCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.AssetControllerConstants;
import com.dharbor.sintaxterrors.asset_service.dto.request.asset.UpdateAssetRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetResponse;
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

@Tag(name = AssetControllerConstants.AssetSwaggerDoc.TAG_NAME,
        description = AssetControllerConstants.AssetSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = AssetControllerConstants.AssetPath.BASE_PATH)
@RequestScope
@RequiredArgsConstructor
@RestController
public class UpdateAssetController {

    private final UpdateAssetCommand updateAssetCommand;

    @Operation(summary = AssetControllerConstants.AssetSwaggerDoc.TAG_UPDATE_ASSET)
    @PutMapping()
    public CommonResponse<AssetResponse> updateAsset(@Valid @RequestBody UpdateAssetRequest request) {
        updateAssetCommand.setInput(request);
        (new SafeCommandExecutor<UpdateAssetRequest, AssetResponse>()).safeExecution(updateAssetCommand);
        return new CommonResponse<>(
                updateAssetCommand.getOutput(),
                HttpStatus.OK,
                HttpStatus.OK.name());
    }
}
