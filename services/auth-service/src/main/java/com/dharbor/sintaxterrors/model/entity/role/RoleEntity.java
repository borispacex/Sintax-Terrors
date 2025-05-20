package com.dharbor.sintaxterrors.model.entity.role;

import com.dharbor.sintaxterrors.model.constant.RoleEntityConstant.RoleTable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = RoleTable.NAME)
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = RoleTable.Id.NAME, nullable = false)
    private Integer id;

    @Column(name = RoleTable.Name.NAME)
    private String name;

    @Column(name = RoleTable.Description.NAME)
    private String description;

    @Column(name = RoleTable.IsActive.NAME)
    private Boolean isActive;
}
