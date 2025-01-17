package org.example.botoutlay.dbConfig.entityAndService.repository;

import org.example.botoutlay.dbConfig.entityAndService.entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    Optional<ExpenseCategory> findByIdAndDeletedFalse(Long id);
    boolean existsByNameAndDeletedFalse(String name);
    List<ExpenseCategory> findAllByDeletedFalse();

}
