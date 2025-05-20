package com.dharbor.sintaxterrors.model.entity.user;

import com.dharbor.sintaxterrors.model.constant.UserEntityConstant.UserTable;
import com.dharbor.sintaxterrors.model.entity.role.RoleEntity;
import com.dharbor.sintaxterrors.model.entity.user.enums.UserState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = UserTable.TABLE_NAME)
public class UserEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = UserTable.Username.NAME, length = 20, nullable = false)
    private String username;

    @Column(name = UserTable.Password.NAME, length = 150, nullable = false)
    private String password;

    @Column(name = UserTable.State.NAME, length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserState state;

    @Column(name = UserTable.DateJoined.NAME)
    private LocalDateTime dateJoined;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = RoleEntity.class)
    @JoinColumn(name = UserTable.Role.NAME, nullable = false, referencedColumnName = UserTable.Id.NAME)
    private RoleEntity role;
}

