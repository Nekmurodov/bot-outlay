package org.example.botoutlay.dbConfig.entityAndService.repository;

import org.example.botoutlay.dbConfig.entityAndService.entity.Currency;
import org.example.botoutlay.dbConfig.entityAndService.enums.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> findByCcy(CurrencyType currencyType);
}
