package com.dharbor.sintaxterrors.model.entity;


import jakarta.persistence.metamodel.SingularAttribute;

public class NotificationEntity_ {

    public static volatile SingularAttribute<NotificationEntity, Long> id;
    public static volatile SingularAttribute<NotificationEntity, Integer> userId;

    public static volatile SingularAttribute<NotificationEntity, String> head;

    public static volatile SingularAttribute<NotificationEntity, String> body;

    public static volatile SingularAttribute<NotificationEntity, String> template;
    public static volatile SingularAttribute<NotificationEntity, Boolean> isRead;

}
