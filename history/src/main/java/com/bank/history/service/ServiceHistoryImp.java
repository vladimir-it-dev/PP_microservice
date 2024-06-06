package com.bank.history.service;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.exception.HistoryNotFoundException;
import com.bank.history.mappers.HistoryMappers;
import com.bank.history.entity.History;
import com.bank.history.repository.RepositoryHistory;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ServiceHistoryImp class предназначен для обработки данных при получении, сохранении, обновлении, удалении объекта History в базу данных.
 */
@Service
@Transactional
public class ServiceHistoryImp implements ServiceHistory {

    private final RepositoryHistory repositoryHistory;
    private final HistoryMappers mapper = Mappers.getMapper(HistoryMappers.class);

    @Autowired
    public ServiceHistoryImp(RepositoryHistory repositoryHistory) {
        this.repositoryHistory = repositoryHistory;
    }

    public List<HistoryDTO> findAllHistory() {
        return repositoryHistory.findAll().stream().map(history -> mapper.toDTO(history)).collect(Collectors.toList());
    }

    public HistoryDTO findByIdHistory(Long id) {
        History history = repositoryHistory.findById(id).orElseThrow(HistoryNotFoundException::new);
        return mapper.toDTO(history);
    }

    public History saveHistory(HistoryDTO history) {
        History save = repositoryHistory.save(mapper.toEntity(history));
        return save;
    }

    public History updateHistory(HistoryDTO historyDTO) {
        Long id = historyDTO.getId();
        History historyID = repositoryHistory.findById(id).orElseThrow(HistoryNotFoundException::new);
        return repositoryHistory.save(mapper.toEntity(historyDTO));
    }

    public void deleteHistory(Long id) {
        repositoryHistory.findById(id).orElseThrow(HistoryNotFoundException::new);
        repositoryHistory.deleteById(id);
    }
}
