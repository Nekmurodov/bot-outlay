package org.example.botoutlay.dbConfig.controller;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.payload.OutcomeCreatDto;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.example.botoutlay.dbConfig.entityAndService.service.OutcomeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/outcome/")
public class OutcomeController {

    private final OutcomeService outcomeService;

    @PostMapping("/creat")
    public ResponseData<?> creat(@RequestBody OutcomeCreatDto outcomeCreatDto) {
        return this.outcomeService.creat(outcomeCreatDto);
    }

    @PutMapping("update/{outcomeId}")
    public ResponseData<?> update(@PathVariable Long outcomeId, OutcomeCreatDto outcomeCreatDto) {
        return this.outcomeService.update(outcomeId, outcomeCreatDto);
    }

    @GetMapping("/get/{outcomeId}")
    public ResponseData<?> get(@PathVariable Long outcomeId) {
        return this.outcomeService.get(outcomeId);
    }

    @GetMapping("/get-all")
    public ResponseData<?> getAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return this.outcomeService.getAll(page, size);
    }

    @DeleteMapping("delete/{outcomeId}")
    public ResponseData<?> delete(@PathVariable Long outcomeId) {
        return this.outcomeService.delete(outcomeId);
    }

}
