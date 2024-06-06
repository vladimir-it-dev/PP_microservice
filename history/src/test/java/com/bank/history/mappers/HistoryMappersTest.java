package com.bank.history.mappers;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.entity.History;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HistoryMappersTest {
    private HistoryMappers mapper = Mappers.getMapper(HistoryMappers.class);

    @Test
    void toDTO() {
        History history = new History();
        history.setId(1L);
        history.setAccount_audit_id(4L);
        history.setAnti_fraud_audit_id(3L);
        history.setAuthorization_audit_id(7L);
        history.setProfile_audit_id(8L);
        history.setPublic_bank_info_audit_id(9L);
        history.setTransfer_audit_id(10L);

        HistoryDTO historyDTO = mapper.toDTO(history);

        assertNotNull(historyDTO);
        assertEquals(history.getId(), historyDTO.getId());
        assertEquals(history.getTransfer_audit_id(), historyDTO.getTransfer_audit_id());
        assertEquals(history.getProfile_audit_id(), historyDTO.getProfile_audit_id());
        assertEquals(history.getPublic_bank_info_audit_id(), historyDTO.getPublic_bank_info_audit_id());
        assertEquals(history.getAnti_fraud_audit_id(), historyDTO.getAnti_fraud_audit_id());
        assertEquals(history.getAuthorization_audit_id(), historyDTO.getAuthorization_audit_id());
        assertEquals(history.getAccount_audit_id(), historyDTO.getAccount_audit_id());
    }

    @Test
    void toEntity() {
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setId(2L);
        historyDTO.setAccount_audit_id(7L);
        historyDTO.setAnti_fraud_audit_id(9L);
        historyDTO.setAuthorization_audit_id(11L);
        historyDTO.setProfile_audit_id(5L);
        historyDTO.setPublic_bank_info_audit_id(12L);
        historyDTO.setTransfer_audit_id(10L);

        History history = mapper.toEntity(historyDTO);

        assertNotNull(history);
        assertEquals(historyDTO.getId(), history.getId());
        assertEquals(historyDTO.getTransfer_audit_id(), history.getTransfer_audit_id());
        assertEquals(historyDTO.getProfile_audit_id(), history.getProfile_audit_id());
        assertEquals(historyDTO.getPublic_bank_info_audit_id(), history.getPublic_bank_info_audit_id());
        assertEquals(historyDTO.getAnti_fraud_audit_id(), history.getAnti_fraud_audit_id());
        assertEquals(historyDTO.getAuthorization_audit_id(), history.getAuthorization_audit_id());
        assertEquals(historyDTO.getAccount_audit_id(), history.getAccount_audit_id());
    }
}
