package com.dharbor.sintaxterrors.asset_service.mapper;


import com.dharbor.sintaxterrors.asset_service.dto.request.employee.CreateEmployeeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.employee.UpdateEmployeeRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.employee.EmployeeShortResponse;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ResponseConstant;
import org.apache.commons.text.WordUtils;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = {ResponseConstant.SuccessResponse.class})
public interface EmployeeMapper {
    default EmployeeEntity mapperToEmployeeEntityFrom(CreateEmployeeRequest source) {
        EmployeeEntity target = new EmployeeEntity();
        target.setCi(source.getCi());
        target.setFirstName(WordUtils.capitalizeFully(source.getFirstName()));
        target.setMiddleName(WordUtils.capitalizeFully(source.getMiddleName()));
        target.setLastName(WordUtils.capitalizeFully(source.getLastName()));
        target.setSecondLastName(WordUtils.capitalizeFully(source.getSecondLastName()));
        target.setPersonalEmail(source.getPersonalEmail());
        target.setWorkEmail(source.getWorkEmail());
        target.setBirthDate(source.getBirthDate());
        target.setCountry(WordUtils.capitalizeFully(source.getCountry()));
        target.setCity(source.getCity());
        target.setCellphoneNumber(source.getCellphoneNumber());
        target.setStatus(source.getStatus());
        target.setSelectedImageID(source.getSelectedImageID());
        target.setUploadedImageID(source.getUploadedImageID());
        target.setUserId(source.getUserId());
        return target;
    }

    default void mapperToUpdateEmployeeRequestFrom(UpdateEmployeeRequest source) {
        source.setFirstName(WordUtils.capitalizeFully(source.getFirstName()));
        source.setMiddleName(WordUtils.capitalizeFully(source.getMiddleName()));
        source.setLastName(WordUtils.capitalizeFully(source.getLastName()));
        source.setSecondLastName(WordUtils.capitalizeFully(source.getSecondLastName()));
        source.setCountry(WordUtils.capitalizeFully(source.getCountry()));
    }

    void updateEmployeeRequestFrom(UpdateEmployeeRequest source, @MappingTarget EmployeeEntity target);

    EmployeeResponse mapperToEmployeeResponseFrom(EmployeeEntity source);

    EmployeeShortResponse mapperToShortEmployeeResponseFrom(EmployeeEntity source);

//    TODO: Take a look later when the user service is ready
//    default EmployeeResponse mapperToEmployeeUserResponseFrom(EmployeeEntity source, UserShortResponse userResponse) {
//        EmployeeResponse target = mapperToEmployeeResponseFrom(source);
//        target.setUser(userResponse);
//        return target;
//    }
}
