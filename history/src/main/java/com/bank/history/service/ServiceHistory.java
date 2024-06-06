package com.bank.history.service;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.entity.History;

import java.util.List;

/**
 * ServiceHistory interface предназначен для установления слабой связи между объектами.
 */
public interface ServiceHistory {
    List<HistoryDTO> findAllHistory();

    HistoryDTO findByIdHistory(Long id);

    History saveHistory(HistoryDTO history);

    History updateHistory(HistoryDTO history);

    void deleteHistory(Long id);
}
