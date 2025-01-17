package org.example.botoutlay.dbConfig.controller;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.payload.IncomeCreatDto;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.example.botoutlay.dbConfig.entityAndService.service.IncomeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/income/")
public class IncomeController {
    private final IncomeService incomeService;

    @PostMapping("/creat")
    public ResponseData<?> creat(@RequestBody IncomeCreatDto incomeCreatDto) {
        return this.incomeService.creat(incomeCreatDto);
    }

    @PutMapping("update/{incomeId}")
    public ResponseData<?> update(@PathVariable Long incomeId, IncomeCreatDto incomeCreatDto) {
        return this.incomeService.update(incomeId, incomeCreatDto);
    }

    @GetMapping("/get/{incomeId}")
    public ResponseData<?> get(@PathVariable Long incomeId) {
        return this.incomeService.get(incomeId);
    }

    @GetMapping("/get-all")
    public ResponseData<?> getAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return this.incomeService.getAll(page, size);
    }

    @DeleteMapping("delete/{incomeId}")
    public ResponseData<?> delete(@PathVariable Long incomeId) {
        return this.incomeService.delete(incomeId);
    }
}
