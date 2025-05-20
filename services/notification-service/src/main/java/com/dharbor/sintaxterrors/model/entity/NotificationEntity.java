package com.dharbor.sintaxterrors.model.entity;

import com.dharbor.sintaxterrors.model.constant.NotificationEntityConstant.NotificationTable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = NotificationTable.TABLE_NAME)
public class NotificationEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = NotificationTable.UserId.NAME)
    private Integer userId;

    @Column(name = NotificationTable.Head.NAME)
    private String head;

    @Column(name = NotificationTable.Body.NAME)
    private String body;

    @Column(name = NotificationTable.Template.NAME)
    private String template;

    @Column(name = NotificationTable.IsRead.NAME)
    private Boolean isRead;
}
