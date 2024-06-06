package com.bank.history.util;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.service.ServiceHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Util class предназначен для сохранения объекта History в базу данных.
 */
@Component
@Slf4j
public class Util {
    private final ServiceHistory serviceHistory;

    @Autowired
    public Util(ServiceHistory serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    @PostConstruct
    private void addHistoryDatabase() {
        log.info("Добавление истории аудита в базу данных");
        HistoryDTO historyDTO1 = HistoryDTO.builder()
                .account_audit_id(1L)
                .authorization_audit_id(1L)
                .profile_audit_id(1L)
                .public_bank_info_audit_id(1L)
                .transfer_audit_id(1L)
                .anti_fraud_audit_id(1L)
                .build();
        HistoryDTO historyDTO2 = HistoryDTO.builder()
                .account_audit_id(2L)
                .authorization_audit_id(2L)
                .profile_audit_id(2L)
                .public_bank_info_audit_id(2L)
                .transfer_audit_id(2L)
                .anti_fraud_audit_id(2L)
                .build();
        HistoryDTO historyDTO3 = HistoryDTO.builder()
                .account_audit_id(3L)
                .authorization_audit_id(3L)
                .profile_audit_id(3L)
                .public_bank_info_audit_id(3L)
                .transfer_audit_id(3L)
                .anti_fraud_audit_id(3L)
                .build();
        HistoryDTO historyDTO4 = HistoryDTO.builder()
                .account_audit_id(4L)
                .authorization_audit_id(4L)
                .profile_audit_id(4L)
                .public_bank_info_audit_id(4L)
                .transfer_audit_id(4L)
                .anti_fraud_audit_id(4L)
                .build();
        HistoryDTO historyDTO5 = HistoryDTO.builder()
                .account_audit_id(5L)
                .authorization_audit_id(5L)
                .profile_audit_id(5L)
                .public_bank_info_audit_id(5L)
                .transfer_audit_id(5L)
                .anti_fraud_audit_id(5L)
                .build();
        serviceHistory.saveHistory(historyDTO1);
        serviceHistory.saveHistory(historyDTO2);
        serviceHistory.saveHistory(historyDTO3);
        serviceHistory.saveHistory(historyDTO4);
        serviceHistory.saveHistory(historyDTO5);
    }
}
