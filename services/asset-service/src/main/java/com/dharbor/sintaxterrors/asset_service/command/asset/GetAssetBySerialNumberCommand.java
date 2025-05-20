package com.dharbor.sintaxterrors.asset_service.command.asset;

import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.AssetExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.service.AssetService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class GetAssetBySerialNumberCommand
        extends SafeAbstractCommand<String, AssetResponse>
        implements PreExecutorCommand, PostExecutorCommand {

    private final AssetService assetService;

    @Override
    public void preExecute() {
        log.info("GetAssetBySerialNumberCommand - PreExecute");
        if (!assetService.existsBySerialNumber(this.input)) {
            throw new ProcessErrorException(AssetExceptionConstants.ASSET_NOT_FOUND);
        }
    }

    @Override
    public void execute() {
        log.info("GetAssetBySerialNumberCommand - Execute");
        this.output = assetService.getAssetBySerialNumber(this.input);
    }

    @Override
    public void postExecute() {
        log.info("GetAssetBySerialNumberCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(AssetExceptionConstants.FAILED_TO_GET_ASSET);
        }
    }
}
