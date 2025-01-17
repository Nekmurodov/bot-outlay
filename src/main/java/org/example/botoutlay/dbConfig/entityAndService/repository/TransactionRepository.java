package org.example.botoutlay.dbConfig.entityAndService.repository;

import org.example.botoutlay.dbConfig.entityAndService.entity.Transaction;
import org.example.botoutlay.dbConfig.entityAndService.enums.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByIdAndDeletedFalse(Long id);
    Page<Transaction> findAllByDeletedFalseAndTransactionType(Pageable pageable, TransactionType transactionType);
}
