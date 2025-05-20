package com.dharbor.sintaxterrors.asset_service.model.entity.employee;

import com.dharbor.sintaxterrors.asset_service.model.constant.EmployeeEntityConstants.EmployeeTable;
import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.enums.employee.EmployeeStatus;
import com.dharbor.sintaxterrors.asset_service.model.entity.team.TeamEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.transaction.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = EmployeeTable.NAME)
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = EmployeeTable.Id.NAME, nullable = false)
    private Long id;

    @Column(name = EmployeeTable.Ci.NAME, nullable = false, unique = true, length = EmployeeTable.Ci.LENGTH)
    private String ci;

    @Column(name = EmployeeTable.FirstName.NAME, length = EmployeeTable.FirstName.LENGTH)
    private String firstName;

    @Column(name = EmployeeTable.MiddleName.NAME, length = EmployeeTable.MiddleName.LENGTH)
    private String middleName;

    @Column(name = EmployeeTable.LastName.NAME, length = EmployeeTable.LastName.LENGTH)
    private String lastName;

    @Column(name = EmployeeTable.SecondLastName.NAME, length = EmployeeTable.SecondLastName.LENGTH)
    private String secondLastName;

    @Column(name = EmployeeTable.PersonalEmail.NAME)
    private String personalEmail;

    @Column(name = EmployeeTable.WorkEmail.NAME)
    private String workEmail;

    @Column(name = EmployeeTable.BirthDate.NAME)
    private LocalDate birthDate;

    @Column(name = EmployeeTable.Country.NAME, length = EmployeeTable.Country.LENGTH)
    private String country;

    @Column(name = EmployeeTable.City.NAME, length = EmployeeTable.City.LENGTH)
    @Enumerated(EnumType.STRING)
    private BoliviaCity city;

    @Column(name = EmployeeTable.CellphoneNumber.NAME, length = EmployeeTable.CellphoneNumber.LENGTH)
    private String cellphoneNumber;

    @Column(name = EmployeeTable.Status.NAME, length = EmployeeTable.Status.LENGTH)
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @Column(name = EmployeeTable.UserId.NAME)
    private Integer userId;

//    TODO: Take a look later when the user service is ready
//    @Transient
//    private UserShortResponse user;

    @OneToOne(mappedBy = EmployeeTable.TeamJoin.PROJECT_MANAGER_MAPPER, fetch = FetchType.LAZY)
    private TeamEntity projectManagerTeam;

    @ManyToOne(fetch = FetchType.LAZY, optional = true, targetEntity = TeamEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = EmployeeTable.TeamJoin.NAME)
    private TeamEntity team;

    @OneToMany(mappedBy = EmployeeTable.Transaction.MAPPER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TransactionEntity> transactions;

    @Column(name = EmployeeTable.SelectedImageID.NAME, length = EmployeeTable.SelectedImageID.LENGTH)
    private String selectedImageID;

    @Column(name = EmployeeTable.UploadedImageID.NAME, length = EmployeeTable.UploadedImageID.LENGTH)
    private String uploadedImageID;
}
