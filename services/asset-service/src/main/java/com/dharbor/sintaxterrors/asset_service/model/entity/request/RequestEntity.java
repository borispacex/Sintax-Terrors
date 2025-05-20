package com.dharbor.sintaxterrors.asset_service.model.entity.request;

import com.dharbor.sintaxterrors.asset_service.enums.request.RequestType;
import com.dharbor.sintaxterrors.asset_service.enums.shared.Status;
import com.dharbor.sintaxterrors.asset_service.model.constant.RequestEntityConstants.RequestTable;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = RequestTable.NAME)
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = RequestTable.Id.NAME, nullable = false)
    private Integer id;

    @Column(name = RequestTable.Title.NAME)
    private String title;

    @Column(name = RequestTable.Description.NAME)
    private String description;

    @Column(name = RequestTable.Status.NAME)
    private Status status;

    @Column(name = RequestTable.CreatedAt.NAME)
    private LocalDate createdAt;

    @Column(name = RequestTable.Type.NAME)
    private RequestType type;

    @Column(name = RequestTable.AssetIds.NAME)
    private String assets;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = EmployeeEntity.class, cascade = CascadeType.DETACH)
    @JoinColumn(name = RequestTable.EmployeeJoin.NAME)
    private EmployeeEntity employee;
}
