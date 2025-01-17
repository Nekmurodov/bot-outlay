package org.example.botoutlay.dbConfig.entityAndService.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.botoutlay.dbConfig.entityAndService.enums.CurrencyType;
import org.example.botoutlay.dbConfig.entityAndService.enums.PaymentMethod;
import org.example.botoutlay.dbConfig.entityAndService.enums.TransactionType;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutcomeCreatDto {
    private double amount;
    private CurrencyType currencyType;
    private PaymentMethod paymentMethod;
    private TransactionType transactionType;
    private LocalDate date;
    private Long expenseCategoryId;
    private String description;
    private String filePath;
}
