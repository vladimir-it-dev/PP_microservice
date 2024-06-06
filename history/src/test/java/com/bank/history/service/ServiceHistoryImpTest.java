package com.bank.history.service;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.entity.History;
import com.bank.history.mappers.HistoryMappers;
import com.bank.history.repository.RepositoryHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ServiceHistoryImpTest {
    private RepositoryHistory repositoryHistory;
    private HistoryMappers mapper = Mappers.getMapper(HistoryMappers.class);
    private ServiceHistoryImp serviceHistoryImp;

    @BeforeEach
    void setUp() {
        repositoryHistory = Mockito.mock(RepositoryHistory.class);
        serviceHistoryImp = new ServiceHistoryImp(repositoryHistory);
    }

    @Test
    void findAllHistory() {
        // создаем список объектов History, который должен вернуться из базы данных
        List<History> historyListDB = new ArrayList<>();
        History history1 = new History(1L, 1L, 1L, 1L, 1L, 1L, 1L);
        History history2 = new History(2L, 2L, 2L, 2L, 2L, 2L, 2L);
        historyListDB.add(history1);
        historyListDB.add(history2);

        Mockito.when(repositoryHistory.findAll()).thenReturn(historyListDB);
        // Вызываем метод findAllHistory() сервиса
        List<HistoryDTO> expectedAllHistory = serviceHistoryImp.findAllHistory();
        // Проверяем, что размер списка объектов DTO равен размеру списка объектов исходной сущности
        assertEquals(expectedAllHistory.size(), historyListDB.size());
        // Проверяем, что все поля объектов DTO заполнены данными из объектов исходной сущности
        for (int i = 0; i < expectedAllHistory.size(); i++) {
            HistoryDTO resultDTO = expectedAllHistory.get(i);
            History sourceEntity = historyListDB.get(i);
            assertEquals(resultDTO.getId(), sourceEntity.getId());
            assertEquals(resultDTO.getAnti_fraud_audit_id(), sourceEntity.getAnti_fraud_audit_id());
            assertEquals(resultDTO.getAccount_audit_id(), sourceEntity.getAccount_audit_id());
            assertEquals(resultDTO.getProfile_audit_id(), sourceEntity.getProfile_audit_id());
            assertEquals(resultDTO.getPublic_bank_info_audit_id(), sourceEntity.getPublic_bank_info_audit_id());
            assertEquals(resultDTO.getAuthorization_audit_id(), sourceEntity.getAuthorization_audit_id());
            assertEquals(resultDTO.getTransfer_audit_id(), sourceEntity.getTransfer_audit_id());
        }
        Mockito.verify(repositoryHistory, Mockito.times(1)).findAll();
    }

    @Test
    void findByIdHistory() {
        Long id = 1L;
        History history = new History();
        history.setId(id);

        Mockito.when(repositoryHistory.findById(id)).thenReturn(Optional.of(history));
        HistoryDTO result = serviceHistoryImp.findByIdHistory(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(result.getId(), history.getId());
        Mockito.verify(repositoryHistory, Mockito.times(1)).findById(id);
        Mockito.verifyNoMoreInteractions(repositoryHistory);
    }

    @Test
    void saveHistory() {
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setAccount_audit_id(1L);
        historyDTO.setAuthorization_audit_id(2L);
        historyDTO.setAnti_fraud_audit_id(5L);
        historyDTO.setTransfer_audit_id(6L);
        historyDTO.setPublic_bank_info_audit_id(10L);
        historyDTO.setProfile_audit_id(11L);

        History expectedHistoryEntity = new History();
        expectedHistoryEntity.setId(1L);
        expectedHistoryEntity.setAccount_audit_id(1L);
        expectedHistoryEntity.setAuthorization_audit_id(2L);
        expectedHistoryEntity.setAnti_fraud_audit_id(5L);
        expectedHistoryEntity.setTransfer_audit_id(6L);
        expectedHistoryEntity.setPublic_bank_info_audit_id(10L);
        expectedHistoryEntity.setProfile_audit_id(11L);

        Mockito.when(repositoryHistory.save(mapper.toEntity(historyDTO))).thenReturn(expectedHistoryEntity);

        History savedHistory = serviceHistoryImp.saveHistory(historyDTO);

        assertNull(historyDTO.getId());
        assertEquals(expectedHistoryEntity.getPublic_bank_info_audit_id(), savedHistory.getPublic_bank_info_audit_id());
        assertEquals(expectedHistoryEntity.getTransfer_audit_id(), savedHistory.getTransfer_audit_id());
        assertEquals(expectedHistoryEntity.getProfile_audit_id(), savedHistory.getProfile_audit_id());
        assertEquals(expectedHistoryEntity.getAnti_fraud_audit_id(), savedHistory.getAnti_fraud_audit_id());
        assertEquals(expectedHistoryEntity.getAuthorization_audit_id(), savedHistory.getAuthorization_audit_id());
        assertEquals(expectedHistoryEntity.getAccount_audit_id(), savedHistory.getAccount_audit_id());

        assertTrue(expectedHistoryEntity.equals(savedHistory));
        Mockito.verify(repositoryHistory, Mockito.times(1)).save(mapper.toEntity(historyDTO));
    }

    @Test
    void updateHistory() {
        HistoryDTO historyDTO = new HistoryDTO(1L, 1L, 1L, 1L, 1L, 1L, 1L);
        History expectedHistory = new History(1L, 1L, 1L, 1L, 1L, 1L, 1L);

        Mockito.when(repositoryHistory.findById(historyDTO.getId())).thenReturn(Optional.of(expectedHistory));
        Mockito.when(repositoryHistory.save(mapper.toEntity(historyDTO))).thenReturn(expectedHistory);

        History updatedHistory = serviceHistoryImp.updateHistory(historyDTO);
        assertEquals(expectedHistory, updatedHistory);
        assertNotNull(updatedHistory);
    }

    @Test
    void deleteHistory() {
        Long id = 1L;
        Mockito.when(repositoryHistory.findById(id)).thenReturn(Optional.of(new History()));
        assertDoesNotThrow(() -> serviceHistoryImp.deleteHistory(id));
        Mockito.verify(repositoryHistory).findById(id);
        Mockito.verify(repositoryHistory, Mockito.times(1)).deleteById(id);
    }
}