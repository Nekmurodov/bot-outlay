package org.example.botoutlay.dbConfig.entityAndService.mapper;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.Transaction;
import org.example.botoutlay.dbConfig.entityAndService.payload.IncomeCreatDto;
import org.example.botoutlay.dbConfig.entityAndService.payload.IncomeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IncomeMapper {

    public Transaction toEntity(IncomeCreatDto incomeCreatDto){
        Transaction transaction = new Transaction();
        transaction.setAmount(incomeCreatDto.getAmount());
        transaction.setCurrencyType(incomeCreatDto.getCurrencyType());
        transaction.setPaymentMethod(incomeCreatDto.getPaymentMethod());
        transaction.setTransactionType(incomeCreatDto.getTransactionType());
        transaction.setDescription(incomeCreatDto.getDescription());
        transaction.setFilePath(incomeCreatDto.getFilePath());
        transaction.setPaymentStatus(incomeCreatDto.getPaymentStatus());
        return transaction;
    }

    public IncomeDto toDto(Transaction transaction){
        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setId(transaction.getId());
        incomeDto.setAmount(transaction.getAmount());
        incomeDto.setCurrencyType(transaction.getCurrencyType());
        incomeDto.setPaymentMethod(transaction.getPaymentMethod());
        incomeDto.setTransactionType(transaction.getTransactionType());
        incomeDto.setDescription(transaction.getDescription());
        incomeDto.setFilePath(transaction.getFilePath());
        incomeDto.setPaymentStatus(transaction.getPaymentStatus());
        incomeDto.setClient(transaction.getClient());
        incomeDto.setServiceType(transaction.getServiceType());
        incomeDto.setAmountUz(transaction.getAmountUz());
        incomeDto.setCurrencyTypeUz(transaction.getCurrencyTypeUz());
        return incomeDto;
    }

    public Transaction updateEntity(Transaction transaction, IncomeCreatDto incomeCreatDto){
        if (incomeCreatDto.getCurrencyType() != null){
            transaction.setCurrencyType(incomeCreatDto.getCurrencyType());
        }
        if (incomeCreatDto.getAmount() != transaction.getAmount()){
            transaction.setAmount(incomeCreatDto.getAmount());
        }
        if (incomeCreatDto.getPaymentMethod() != null){
            transaction.setPaymentMethod(incomeCreatDto.getPaymentMethod());
        }
        if (incomeCreatDto.getTransactionType() != null){
            transaction.setTransactionType(incomeCreatDto.getTransactionType());
        }
        if (incomeCreatDto.getDescription() != null){
            transaction.setDescription(incomeCreatDto.getDescription());
        }
        if (incomeCreatDto.getFilePath() != null){
            transaction.setFilePath(incomeCreatDto.getFilePath());
        }
        if (incomeCreatDto.getPaymentStatus() != null){
            transaction.setPaymentStatus(incomeCreatDto.getPaymentStatus());
        }
        return transaction;
    }

    public List<IncomeDto> toDto(List<Transaction> transactions) {
        List<IncomeDto> incomeDto = new ArrayList<>();
        for (Transaction transaction : transactions) {
            incomeDto.add(toDto(transaction));
        }
        return incomeDto;
    }

}
