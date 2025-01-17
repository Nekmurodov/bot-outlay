package org.example.botoutlay.dbConfig.entityAndService.service;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.ExpenseCategory;
import org.example.botoutlay.dbConfig.entityAndService.mapper.ExpenseCategoryMapper;
import org.example.botoutlay.dbConfig.entityAndService.payload.ExpenseCategoryCreatDto;
import org.example.botoutlay.dbConfig.entityAndService.repository.ExpenseCategoryRepository;
import org.example.botoutlay.dbConfig.exception.AlreadyExistException;
import org.example.botoutlay.dbConfig.exception.NotFoundException;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseCategoryMapper expenseCategoryMapper;

    public ResponseData<?> creat(ExpenseCategoryCreatDto expenseCategoryCreatDto) {
        if (this.expenseCategoryRepository.existsByNameAndDeletedFalse(expenseCategoryCreatDto.getName())) {
            throw new AlreadyExistException("Expense category already exists");
        }
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setName(expenseCategoryCreatDto.getName());
        this.expenseCategoryRepository.save(expenseCategory);
        return ResponseData.successResponse("Expense category created");
    }

    public ResponseData<?> update(Long expenseId, ExpenseCategoryCreatDto expenseCategoryCreatDto) {
        Optional<ExpenseCategory> expenseCategoryOptional = this.expenseCategoryRepository.findByIdAndDeletedFalse(expenseId);
        if (expenseCategoryOptional.isEmpty()){
            throw new NotFoundException("Expense category not found");
        }
        if (this.expenseCategoryRepository.existsByNameAndDeletedFalse(expenseCategoryCreatDto.getName())) {
            throw new AlreadyExistException("Expense category already exists");
        }
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setName(expenseCategoryCreatDto.getName());
        this.expenseCategoryRepository.save(expenseCategory);
        return ResponseData.successResponse("Expense category created");
    }

    public ResponseData<?> get(Long expenseId) {
        Optional<ExpenseCategory> expenseCategoryOptional = this.expenseCategoryRepository.findByIdAndDeletedFalse(expenseId);
        if (expenseCategoryOptional.isEmpty()){
            throw new NotFoundException("Expense category not found");
        }
        return ResponseData.successResponse(this.expenseCategoryMapper.toDto(expenseCategoryOptional.get()));
    }

    public ResponseData<?> getAll() {
        List<ExpenseCategory> expenseCategoryList = this.expenseCategoryRepository.findAllByDeletedFalse();
        if (expenseCategoryList.isEmpty()){
            throw new NotFoundException("Expense category not found");
        }
        return ResponseData.successResponse(this.expenseCategoryMapper.toDto(expenseCategoryList));
    }

    public ResponseData<?> delete(Long expenseId) {
        Optional<ExpenseCategory> expenseCategoryOptional = this.expenseCategoryRepository.findByIdAndDeletedFalse(expenseId);
        if (expenseCategoryOptional.isEmpty()){
            throw new NotFoundException("Expense category not found");
        }
        ExpenseCategory expenseCategory = expenseCategoryOptional.get();
        expenseCategory.setDeleted(true);
        this.expenseCategoryRepository.save(expenseCategory);
        return ResponseData.successResponse("Expense category deleted");
    }

}
