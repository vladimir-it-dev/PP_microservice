package com.bank.history.mappers;

import com.bank.history.dto.HistoryDTO;
import com.bank.history.entity.History;
import org.mapstruct.Mapper;

/**
 * HistoryMappers interface предназначен для преобразования объекта History в объект HistoryDTO
 * и объекта HistoryDTO в объект History.
 */
@Mapper(componentModel = "spring")
public interface HistoryMappers {
    HistoryDTO toDTO(History history);

    History toEntity(HistoryDTO historyDTO);
}
