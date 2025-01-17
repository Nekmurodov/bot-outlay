package org.example.botoutlay.dbConfig.entityAndService.service;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.ExpenseCategory;
import org.example.botoutlay.dbConfig.entityAndService.entity.Transaction;
import org.example.botoutlay.dbConfig.entityAndService.enums.TransactionType;
import org.example.botoutlay.dbConfig.entityAndService.mapper.OutcomeMapper;
import org.example.botoutlay.dbConfig.entityAndService.payload.OutcomeCreatDto;
import org.example.botoutlay.dbConfig.entityAndService.repository.ExpenseCategoryRepository;
import org.example.botoutlay.dbConfig.entityAndService.repository.TransactionRepository;
import org.example.botoutlay.dbConfig.exception.NotFoundException;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutcomeService {

    private final TransactionRepository transactionRepository;
    private final OutcomeMapper outcomeMapper;
    private final ExpenseCategoryRepository expenseCategoryRepository;

    public ResponseData<?> creat(OutcomeCreatDto outcomeCreatDto) {
        Optional<ExpenseCategory> expenseCategoryOptional = expenseCategoryRepository
                .findByIdAndDeletedFalse(outcomeCreatDto.getExpenseCategoryId());
        if (expenseCategoryOptional.isEmpty()) {
            throw new NotFoundException("Expense category not found");
        }
        if (outcomeCreatDto.getAmount() < 0){
            return new ResponseData<>("The amount is enter incorrectly",false);
        }
        Transaction transaction = this.outcomeMapper.toEntity(outcomeCreatDto);
        transaction.setExpenseCategory(expenseCategoryOptional.get());
        if (outcomeCreatDto.getDate() == null){
            transaction.setDate(LocalDate.now());
        }
        else {
            transaction.setDate(outcomeCreatDto.getDate());
        }
        this.transactionRepository.save(transaction);
        return ResponseData.successResponse(this.outcomeMapper.toDto(transaction));
    }

    public ResponseData<?> update(Long outcomeId, OutcomeCreatDto outcomeCreatDto) {
        Optional<Transaction> transactionOptional = this.transactionRepository.findByIdAndDeletedFalse(outcomeId);
        if (transactionOptional.isEmpty()) {
            throw new NotFoundException("Transaction not found");
        }
        if (outcomeCreatDto.getAmount() < 0){
            return new ResponseData<>("The amount is enter incorrectly",false);
        }
        Optional<ExpenseCategory> expenseCategoryOptional = expenseCategoryRepository
                .findByIdAndDeletedFalse(outcomeCreatDto.getExpenseCategoryId());
        if (expenseCategoryOptional.isEmpty()) {
            throw new NotFoundException("Expense category not found");
        }
        Transaction transaction = this.outcomeMapper.updateEntity(transactionOptional.get(), outcomeCreatDto);
        if (outcomeCreatDto.getDate() == null){
            transaction.setDate(LocalDate.now());
        }
        else {
            transaction.setDate(outcomeCreatDto.getDate());
        }
        transaction.setExpenseCategory(expenseCategoryOptional.get());
        this.transactionRepository.save(transaction);
        return ResponseData.successResponse(this.outcomeMapper.toDto(transaction));
    }

    public ResponseData<?> get(Long outcomeId) {
        Optional<Transaction> transactionOptional = this.transactionRepository.findByIdAndDeletedFalse(outcomeId);
        if (transactionOptional.isEmpty()) {
            throw new NotFoundException("Transaction not found");
        }
        return ResponseData.successResponse(this.outcomeMapper.toDto(transactionOptional.get()));
    }

    public ResponseData<?> delete(Long outcomeId) {
        Optional<Transaction> transactionOptional = this.transactionRepository.findByIdAndDeletedFalse(outcomeId);
        if (transactionOptional.isEmpty()) {
            throw new NotFoundException("Transaction not found");
        }
        Transaction transaction = transactionOptional.get();
        transaction.setDeleted(true);
        this.transactionRepository.save(transaction);
        return ResponseData.successResponse("success");
    }

    public ResponseData<?> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Transaction> transactions = this.transactionRepository.findAllByDeletedFalseAndTransactionType(pageable, TransactionType.EXPENSE);
        if (transactions.isEmpty()) {
            throw new NotFoundException("Transaction not found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", outcomeMapper.toDto(transactions.toList()));
        response.put("total", transactions.getTotalElements());
        response.put("totalPages", transactions.getTotalPages());

        return ResponseData.successResponse(response);
    }
}
