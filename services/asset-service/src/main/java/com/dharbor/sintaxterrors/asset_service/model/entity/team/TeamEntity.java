package com.dharbor.sintaxterrors.asset_service.model.entity.team;

import com.dharbor.sintaxterrors.asset_service.model.constant.TeamEntityConstants.TeamTable;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = TeamTable.NAME)
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TeamTable.Id.NAME, nullable = false)
    private Integer id;

    @Column(name = TeamTable.Name.NAME)
    private String name;

    @Column(name = TeamTable.Description.NAME)
    private String description;

    @Column(name = TeamTable.IsActive.NAME)
    private Boolean isActive;

    @OneToOne(fetch = FetchType.EAGER, optional = false, targetEntity = EmployeeEntity.class)
    @JoinColumn(name = TeamTable.EmployeeJoin.PROJECT_MANAGER)
    private EmployeeEntity projectManager;
}
