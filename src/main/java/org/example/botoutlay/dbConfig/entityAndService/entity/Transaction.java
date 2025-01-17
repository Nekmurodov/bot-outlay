package org.example.botoutlay.dbConfig.entityAndService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.botoutlay.dbConfig.entityAndService.abs.AbsEntity;
import org.example.botoutlay.dbConfig.entityAndService.enums.CurrencyType;
import org.example.botoutlay.dbConfig.entityAndService.enums.PaymentMethod;
import org.example.botoutlay.dbConfig.entityAndService.enums.PaymentStatus;
import org.example.botoutlay.dbConfig.entityAndService.enums.TransactionType;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction extends AbsEntity {

    private double amountUz;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyTypeUz;

    private double amount;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private LocalDate date;

    @ManyToOne
    private ExpenseCategory expenseCategory;

    @ManyToOne
    private Client client;

    @ManyToOne
    private ServiceType serviceType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private String description;

    private String filePath;

}
