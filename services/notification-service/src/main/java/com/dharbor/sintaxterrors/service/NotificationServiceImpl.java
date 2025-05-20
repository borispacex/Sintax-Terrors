package com.dharbor.sintaxterrors.service;


import com.dharbor.sintaxterrors.dto.PaginationResponse;
import com.dharbor.sintaxterrors.dto.request.CreateNotificationRequest;
import com.dharbor.sintaxterrors.dto.request.GetNotificationPageRequest;
import com.dharbor.sintaxterrors.dto.request.UpdateNotificationRequest;
import com.dharbor.sintaxterrors.dto.response.NotificationResponse;
import com.dharbor.sintaxterrors.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.exception.constant.NotificationExceptionConstant;
import com.dharbor.sintaxterrors.mapper.BaseMapper;
import com.dharbor.sintaxterrors.mapper.NotificationMapper;
import com.dharbor.sintaxterrors.model.entity.NotificationEntity;
import com.dharbor.sintaxterrors.model.entity.NotificationEntity_;
import com.dharbor.sintaxterrors.repository.NotificationRepository;
import com.dharbor.sintaxterrors.util.constant.ScopeConstant;
import com.dharbor.sintaxterrors.util.function.Specifications;
import com.dharbor.sintaxterrors.util.function.Utils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class NotificationServiceImpl implements NotificationService {
    private NotificationEntity preEntity;

    private final NotificationMapper notificationMapper;
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationMapper notificationMapper, NotificationRepository notificationRepository) {
        this.notificationMapper = notificationMapper;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public NotificationResponse saveNotification(CreateNotificationRequest notificationRequest) {
        NotificationEntity newNotification = notificationMapper.mapperToNotificationEntityFrom(notificationRequest);
        return notificationMapper.mapperToNotificationResponseFrom(notificationRepository.save(newNotification));
    }

    @Override
    public void validateCreateNotificationRequest(CreateNotificationRequest notificationRequest) {
        if (Utils.isNull(notificationRequest.getHead())) {
            throw new ProcessErrorException(NotificationExceptionConstant.NOTIFICATION_TITLE_NULL_MESSAGE);
        }
        if (Utils.isNull(notificationRequest.getBody())) {
            throw new ProcessErrorException(NotificationExceptionConstant.NOTIFICATION_DESCRIPTION_NULL_MESSAGE);
        }
        if (Utils.isNull(notificationRequest.getTemplate())) {
            throw new ProcessErrorException(NotificationExceptionConstant.NOTIFICATION_TYPE_NULL_MESSAGE);
        }
    }

    @Override
    public NotificationResponse updateNotification(UpdateNotificationRequest notificationRequest) {
        notificationMapper.updateNotificationEntityFrom(notificationRequest, preEntity);
        NotificationEntity postEntity = notificationRepository.save(preEntity);
        return notificationMapper.mapperToNotificationResponseFrom(postEntity);
    }

    @Override
    public void validateUpdateNotificationRequest(UpdateNotificationRequest notificationRequest) {
        Optional<NotificationEntity> notificationEntity = notificationRepository.findById(notificationRequest.getId());
        if (!notificationEntity.isPresent()) {
            throw new ProcessErrorException(String.format(NotificationExceptionConstant.NOTIFICATION_NOT_EXIST_MESSAGE, notificationRequest.getId()));
        }
        preEntity = notificationEntity.get();
    }

    @Override
    public NotificationResponse getNotificationById(Long notificationId) {
        Optional<NotificationEntity> notificationEntity = notificationRepository.findById(notificationId);
        if (!notificationEntity.isPresent()) {
            throw new ProcessErrorException(String.format(NotificationExceptionConstant.NOTIFICATION_NOT_EXIST_MESSAGE, notificationId));
        }
        return notificationMapper.mapperToNotificationResponseFrom(notificationEntity.get());
    }

    @Override
    public PaginationResponse<NotificationResponse> getNotificationPage(GetNotificationPageRequest request) {
        List<NotificationResponse> contract = new ArrayList<>();
        Sort sort = Specifications.buildSorting(request.getOrder(), request.getDirection());
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Page<NotificationEntity> pageResult = notificationRepository.findAll(buildSpecification(request), pageRequest);
        contract.addAll(
                pageResult.getContent().stream()
                        .map(notificationMapper::mapperToNotificationResponseFrom)
                        .collect(Collectors.toList())
        );
        PaginationResponse<NotificationResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setItems(contract);
        BaseMapper.setPaginationMetaData(paginationResponse, pageResult);
        return paginationResponse;
    }

    private Specification<NotificationEntity> buildSpecification(GetNotificationPageRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!Utils.isNull(request.getUserId())) {
                predicates.add(criteriaBuilder.equal(root.get(NotificationEntity_.userId), request.getUserId()));
            }
            if (!Utils.isNull(request.getIsRead())) {
                predicates.add(criteriaBuilder.equal(root.get(NotificationEntity_.isRead), request.getIsRead()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}