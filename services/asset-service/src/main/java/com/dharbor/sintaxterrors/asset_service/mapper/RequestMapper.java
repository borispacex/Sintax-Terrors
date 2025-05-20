package com.dharbor.sintaxterrors.asset_service.mapper;

import com.dharbor.sintaxterrors.asset_service.dto.request.request.CreateRequestRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.request.UpdateRequestRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.request.RequestResponse;
import com.dharbor.sintaxterrors.asset_service.model.entity.request.RequestEntity;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ResponseConstant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {ResponseConstant.SuccessResponse.class})
public interface RequestMapper {

    RequestMapper mapper = Mappers.getMapper(RequestMapper.class);

    RequestEntity mapperToRequestEntity(CreateRequestRequest source);

    RequestResponse mapperToRequestResponse(RequestEntity source);

    void updateRequestEntity(UpdateRequestRequest source, @MappingTarget RequestEntity target);
}