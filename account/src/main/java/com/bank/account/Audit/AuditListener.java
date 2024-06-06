package com.bank.account.Audit;

import com.bank.account.Entity.Audit;
import com.bank.account.Entity.Dto.AccountDetailsDto;
import com.bank.account.Repository.AuditRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * Класс AuditListener служит для прослушивания событий сущностей
 * и сохранения информации об аудите.
 */
@Configurable
@Component
public class AuditListener {

    @Autowired
    private BeanFactory beanFactory;

    private AuditRepository auditRepository;
    private static final ThreadLocal<AccountDetailsDto> currentAccountDetails = new ThreadLocal<>();

    public void setCurrentAccountDetails(AccountDetailsDto accountDetails) {
        currentAccountDetails.set(accountDetails);
    }

    public static AccountDetailsDto getCurrentAccountDetails() {
        return currentAccountDetails.get();
    }


    final Gson gson = new Gson();

    /**
     * Метод prePersist вызывается перед сохранением сущности
     * и сохраняет информацию об аудите операции создания.
     *
     * @param entity Объект сущности, который будет сохранен.
     */
    @PrePersist
    public void prePersist(Object entity) {
        if (auditRepository == null) {
            auditRepository = beanFactory.getBean(AuditRepository.class);
        }
        Audit audit = Audit.builder()
                .entityType(entity.getClass().getSimpleName())
                .operationType("CREATE")
                .createdBy("Григорий")
                .createdAt(LocalDateTime.now())
                .entityJson(gson.toJson(entity))
                .build();
        auditRepository.save(audit);
    }


    /**
     * Метод preUpdate вызывается перед обновлением сущности
     * и сохраняет информацию об аудите операции обновления.
     *
     * @param entity Объект сущности, который будет обновлен.
     */
    @PreUpdate
    public void preUpdate(Object entity) {
        if (auditRepository == null) {
            auditRepository = beanFactory.getBean(AuditRepository.class);
        }
        AccountDetailsDto oldAccountDetails = getCurrentAccountDetails();
        Audit audit = Audit.builder()
                .entityType(entity.getClass().getSimpleName())
                .operationType("UPDATE")
                .modifiedBy("Григорий")
                .modifiedAt(LocalDateTime.now())
                .entityJson(gson.toJson(oldAccountDetails))
                .newEntityJson(gson.toJson(entity))
                .build();
        auditRepository.save(audit);
    }

    /**
     * Метод preRemove вызывается перед удалением сущности
     * и сохраняет информацию об аудите операции удаления.
     *
     * @param entity Объект сущности, который будет удален.
     */
    @PreRemove
    public void preRemove(Object entity) {
        if (auditRepository == null) {
            auditRepository = beanFactory.getBean(AuditRepository.class);
        }
        Audit audit = Audit.builder()
                .entityType(entity.getClass().getSimpleName())
                .operationType("DELETE")
                .modifiedBy("Григорий")
                .modifiedAt(LocalDateTime.now())
                .entityJson(gson.toJson(entity))
                .build();
        auditRepository.save(audit);
    }
}
