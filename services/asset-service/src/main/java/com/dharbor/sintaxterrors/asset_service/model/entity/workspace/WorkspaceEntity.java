package com.dharbor.sintaxterrors.asset_service.model.entity.workspace;

import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.model.constant.WorkspaceEntityConstants.WorkspaceTable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

@Getter
@Setter
@Entity
@Table(name = WorkspaceTable.NAME)
public class WorkspaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = WorkspaceTable.Id.NAME, nullable = false)
    private Long id;

    @Column(name = WorkspaceTable.City.NAME, length = WorkspaceTable.City.LENGTH)
    @Enumerated(EnumType.STRING)
    private BoliviaCity city;

    @Formula("(SELECT city_ FROM workspaces_ WHERE workspaces_.id_ = id_)")
    private String cityName;

    @Column(name = WorkspaceTable.Location.NAME, length = WorkspaceTable.Location.LENGTH)
    private String location;
}
