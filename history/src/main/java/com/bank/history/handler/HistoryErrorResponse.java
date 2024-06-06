package com.bank.history.handler;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * HistoryErrorResponse class предназначен для передачи сообщения, в случаи не нахождения объекта History в базе данных,
 * при возникновении Exception в контроллере ControllerHistory.
 */
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryErrorResponse {
    String message;
    LocalDateTime localDateTime;
}
