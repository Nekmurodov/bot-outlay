package org.example.botoutlay.dbConfig.entityAndService.mapper;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.ExpenseCategory;
import org.example.botoutlay.dbConfig.entityAndService.payload.ExpenseCategoryDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExpenseCategoryMapper {

    public ExpenseCategoryDto toDto(ExpenseCategory expenseCategory) {
        ExpenseCategoryDto expenseCategoryDto = new ExpenseCategoryDto();
        expenseCategoryDto.setId(expenseCategory.getId());
        expenseCategoryDto.setName(expenseCategory.getName());
        return expenseCategoryDto;
    }

    public List<ExpenseCategoryDto> toDto(List<ExpenseCategory> expenseCategories) {
        List<ExpenseCategoryDto> expenseCategoryDto = new ArrayList<>();
        for (ExpenseCategory expenseCategory : expenseCategories) {
            expenseCategoryDto.add(toDto(expenseCategory));
        }
        return expenseCategoryDto;
    }

}
