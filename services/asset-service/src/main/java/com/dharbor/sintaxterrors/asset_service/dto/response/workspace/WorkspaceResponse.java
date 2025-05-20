package com.dharbor.sintaxterrors.asset_service.dto.response.workspace;

import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkspaceResponse {

    private Long id;

    private BoliviaCity city;

    private String location;
}
