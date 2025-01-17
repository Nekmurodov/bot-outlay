package org.example.botoutlay.dbConfig.controller;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.payload.ExpenseCategoryCreatDto;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.example.botoutlay.dbConfig.entityAndService.service.ExpenseCategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/expense-category/")
public class ExpenseCategoryController {
    private final ExpenseCategoryService expenseCategoryService;

    @PostMapping("/creat")
    public ResponseData<?> creat(@RequestBody ExpenseCategoryCreatDto expenseCategoryCreatDto) {
        return this.expenseCategoryService.creat(expenseCategoryCreatDto);
    }

    @PutMapping("update/{expenseId}")
    public ResponseData<?> update(@PathVariable Long expenseId, ExpenseCategoryCreatDto expenseCategoryCreatDto) {
        return this.expenseCategoryService.update(expenseId, expenseCategoryCreatDto);
    }

    @GetMapping("/get/{expenseId}")
    public ResponseData<?> get(@PathVariable Long expenseId) {
        return this.expenseCategoryService.get(expenseId);
    }

    @GetMapping("/get-all")
    public ResponseData<?> getAll() {
        return this.expenseCategoryService.getAll();
    }

    @DeleteMapping("delete/{expenseId}")
    public ResponseData<?> delete(@PathVariable Long expenseId) {
        return this.expenseCategoryService.delete(expenseId);
    }
}
