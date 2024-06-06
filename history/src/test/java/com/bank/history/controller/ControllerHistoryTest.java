package com.bank.history.controller;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.entity.History;
import com.bank.history.exception.HistoryNotFoundException;
import com.bank.history.handler.HistoryErrorResponse;
import com.bank.history.service.ServiceHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControllerHistoryTest {
    private ServiceHistory serviceHistory;
    private ControllerHistory controllerHistory;

    @BeforeEach
    void setUp() {
        serviceHistory = mock(ServiceHistory.class);
        controllerHistory = new ControllerHistory(serviceHistory);
    }

    @Test
    void allHistory() {
        List<HistoryDTO> expectedList = new ArrayList<>();
        expectedList.add(new HistoryDTO(1L, 1L, 1L, 1L, 1L, 1L, 1L));
        expectedList.add(new HistoryDTO(2L, 2L, 2L, 2L, 2L, 2L, 2L));

        when(serviceHistory.findAllHistory()).thenReturn(expectedList);
        ResponseEntity<List<HistoryDTO>> response = controllerHistory.allHistory();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals(expectedList, response.getBody());
        verify(serviceHistory, times(1)).findAllHistory();
    }

    @Test
    void shouldReturnNotFoundWhenListHistoryIsEmpty() {
        List<HistoryDTO> expectedList = new ArrayList<>();

        when(serviceHistory.findAllHistory()).thenReturn(expectedList);
        ResponseEntity<List<HistoryDTO>> response = controllerHistory.allHistory();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void shouldReturnNotFoundWhenListHistoryIsNull() {
        List<HistoryDTO> expectedList = null;

        when(serviceHistory.findAllHistory()).thenReturn(expectedList);
        ResponseEntity<List<HistoryDTO>> response = controllerHistory.allHistory();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getByIdHistory() {
        Long id = 1L;
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setId(id);

        when(serviceHistory.findByIdHistory(id)).thenReturn(historyDTO);
        ResponseEntity<HistoryDTO> responseEntity = controllerHistory.getByIdHistory(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(historyDTO, responseEntity.getBody());
        verify(serviceHistory, times(1)).findByIdHistory(id);
    }

    @Test
    void saveHistory() {
        HistoryDTO historyDTO = new HistoryDTO(null, 1L, 1L, 1L, 1L, 1L, 1L);

        History saveHistory = new History(1L, 1L, 1L, 1L, 1L, 1L, 1L);

        when(serviceHistory.saveHistory(historyDTO)).thenReturn(saveHistory);
        ResponseEntity<History> responseEntity = controllerHistory.saveHistory(historyDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNull(historyDTO.getId());
        assertNotNull(responseEntity.getBody());
        assertEquals(saveHistory, responseEntity.getBody());
        verify(serviceHistory, times(1)).saveHistory(historyDTO);
    }

    @Test
    void updateHistory() {
        HistoryDTO historyDTO = new HistoryDTO(1L, 1L, 1L, 1L, 1L, 1L, 1L);

        History updatedHistory = new History(1L, 1L, 1L, 1L, 1L, 1L, 1L);

        when(serviceHistory.updateHistory(historyDTO)).thenReturn(updatedHistory);
        ResponseEntity<History> responseEntity = controllerHistory.updateHistory(historyDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(updatedHistory, responseEntity.getBody());
        verify(serviceHistory, times(1)).updateHistory(historyDTO);
    }

    @Test
    void deleteHistory() {
        long historyId = 1L;
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setId(historyId);
        ResponseEntity<HistoryDTO> responseEntity = controllerHistory.deleteHistory(historyId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(serviceHistory, times(1)).deleteHistory(historyId);
    }

    @Test
    void testHandleException() {
        HistoryNotFoundException e = new HistoryNotFoundException();

        ResponseEntity<HistoryErrorResponse> response = controllerHistory.handleException(e);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("История аудита с этим ID не найдена.", response.getBody().getMessage());
        assertNotNull(response.getBody().getLocalDateTime());
        assertEquals(LocalDateTime.now().getDayOfYear(), response.getBody().getLocalDateTime().getDayOfYear());
    }
}
