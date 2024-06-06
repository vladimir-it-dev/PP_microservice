package com.bank.antifraud.listener;

import com.bank.antifraud.entity.Audit;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.util.ListenerUtil;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferListener {

    @Autowired ApplicationContext applicationContext;

    ListenerUtil listenerUtil;
    final Gson gson = Converters.registerLocalDateTime(new GsonBuilder()).create();;


    private void init() {
        if (listenerUtil == null) {
            listenerUtil = applicationContext.getBean(ListenerUtil.class);
        }
    }

    /**

     Метод, вызываемый автоматически при сохранении (persist) сущности в базу данных.
     Инициализирует контекст приложения, создает объект Audit, заполняет его данными и сохраняет в репозиторий Audit.
     @param entity Сущность, которая будет сохранена в базу данных.
     */
    @PostPersist
    public void prePersist(Object entity) {
        init();
        Audit audit = Audit.builder()
                .entityType(entity.getClass().getSimpleName())
                .operationType("CREATE")
                .createdBy("me")
//                .createdBy(SecurityContextHolder.getContext().getAuthentication().getName())
                .createdAt(LocalDateTime.now())
                .entityJson(gson.toJson(entity))
                .build();
        listenerUtil.save(audit);
    }

    /**
     Вызывается перед обновлением сущности в базе данных и создает новую запись аудита.
     @param entity обновляемая сущность
     */
    @PostUpdate
    public void preUpdate(Object entity) {
        init();
        String type = entity.getClass().getSimpleName();
        Long jsonId = ((SuspiciousTransfer) entity).getId();
//        String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        String principal = "me";
        Audit oldAudit = listenerUtil.findByTypeAndJsonId(type, jsonId);
        System.out.println(oldAudit);
        Audit audit = Audit.builder()
                .entityType(oldAudit.getEntityType())
                .operationType("UPDATE")
                .createdAt(oldAudit.getCreatedAt())
                .createdBy(oldAudit.getCreatedBy())
                .modifiedBy(principal)
                .modifiedAt(LocalDateTime.now())
                .entityJson(oldAudit.getNewEntityJson()==null?oldAudit.getEntityJson():oldAudit.getNewEntityJson())
                .newEntityJson(gson.toJson(entity))
                .build();
        listenerUtil.save(audit);
    }

    /**
     Метод, вызываемый перед удалением сущности из базы данных.
     Создает запись в таблице Audit с информацией об удалении сущности.
     @param entity удаляемая сущность
     */
    @PreRemove
    public void preRemove(Object entity) {
        init();
        String type = entity.getClass().getSimpleName();
        Long jsonId = ((SuspiciousTransfer) entity).getId();
        Audit oldAudit = listenerUtil.findByTypeAndJsonId(type, jsonId);
        System.out.println(oldAudit);
        Audit audit = Audit.builder()
                .entityType(oldAudit.getEntityType())
                .operationType("DELETE")
                .createdAt(oldAudit.getCreatedAt())
                .createdBy(oldAudit.getCreatedBy())
//                .modifiedBy(SecurityContextHolder.getContext().getAuthentication().getName())
                .modifiedBy("me")
                .modifiedAt(LocalDateTime.now())
                .entityJson(gson.toJson(entity))
                .build();
        listenerUtil.save(audit);
    }
}
