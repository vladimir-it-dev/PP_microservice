package com.bank.history.exception;

import org.springframework.stereotype.Component;

/**
 * HistoryNotFoundException class предназначен для извлечения Exception в контроллере ControllerHistory,
 * в случаи не нахождения объекта History в базе данных.
 */
@Component
public class HistoryNotFoundException extends RuntimeException {
}
