package com.bank.history.repository;

import com.bank.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RepositoryHistory interface предназначен для получения, сохранения, обновления, удаления объекта History в базу данных.
 */
@Repository
public interface RepositoryHistory extends JpaRepository<History, Long> {
}
