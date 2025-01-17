package org.example.botoutlay.dbConfig.entityAndService.mapper;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.Transaction;
import org.example.botoutlay.dbConfig.entityAndService.payload.OutcomeCreatDto;
import org.example.botoutlay.dbConfig.entityAndService.payload.OutcomeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OutcomeMapper {

    public Transaction toEntity(OutcomeCreatDto outcomeCreatDto) {
        Transaction transaction = new Transaction();
        transaction.setAmount(outcomeCreatDto.getAmount());
        transaction.setCurrencyType(outcomeCreatDto.getCurrencyType());
        transaction.setPaymentMethod(outcomeCreatDto.getPaymentMethod());
        transaction.setTransactionType(outcomeCreatDto.getTransactionType());
        transaction.setFilePath(outcomeCreatDto.getFilePath());
        transaction.setDescription(outcomeCreatDto.getDescription());
        return transaction;
    }

    public OutcomeDto toDto(Transaction transaction) {
        OutcomeDto outcomeDto = new OutcomeDto();
        outcomeDto.setId(transaction.getId());
        outcomeDto.setAmount(transaction.getAmount());
        outcomeDto.setCurrencyType(transaction.getCurrencyType());
        outcomeDto.setPaymentMethod(transaction.getPaymentMethod());
        outcomeDto.setTransactionType(transaction.getTransactionType());
        outcomeDto.setFilePath(transaction.getFilePath());
        outcomeDto.setExpenseCategory(transaction.getExpenseCategory());
        outcomeDto.setDate(transaction.getDate());
        outcomeDto.setDescription(transaction.getDescription());
        return outcomeDto;
    }

    public Transaction updateEntity(Transaction transaction, OutcomeCreatDto outcomeDto) {
        if (outcomeDto.getAmount() != transaction.getAmount()) {
            transaction.setAmount(outcomeDto.getAmount());
        }
        if (outcomeDto.getCurrencyType() != null) {
            transaction.setCurrencyType(outcomeDto.getCurrencyType());
        }
        if (outcomeDto.getPaymentMethod() != null) {
            transaction.setPaymentMethod(outcomeDto.getPaymentMethod());
        }
        if (outcomeDto.getTransactionType() != null) {
            transaction.setTransactionType(outcomeDto.getTransactionType());
        }
        if (outcomeDto.getFilePath() != null) {
            transaction.setFilePath(outcomeDto.getFilePath());
        }
        if (outcomeDto.getDescription() != null) {
            transaction.setDescription(outcomeDto.getDescription());
        }
        return transaction;
    }

    public List<OutcomeDto> toDto(List<Transaction> transactions) {
        List<OutcomeDto> outcomeDto = new ArrayList<>();
        for (Transaction transaction : transactions) {
            outcomeDto.add(toDto(transaction));
        }
        return outcomeDto;
    }

}
