package org.example.botoutlay.dbConfig.controller;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.payload.ClientCreatDto;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.example.botoutlay.dbConfig.entityAndService.service.ClientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/client/")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/creat")
    public ResponseData<?> creat(@RequestBody ClientCreatDto clientCreatDto) {
        return this.clientService.creat(clientCreatDto);
    }

    @PutMapping("update/{clientId}")
    public ResponseData<?> update(@PathVariable Long clientId, ClientCreatDto clientDto) {
        return this.clientService.update(clientId, clientDto);
    }

    @GetMapping("/get/{clientId}")
    public ResponseData<?> get(@PathVariable Long clientId) {
        return this.clientService.get(clientId);
    }

    @GetMapping("/get-all")
    public ResponseData<?> getAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return this.clientService.getAll(page, size);
    }

    @DeleteMapping("delete/{clientId}")
    public ResponseData<?> delete(@PathVariable Long clientId) {
        return this.clientService.delete(clientId);
    }
}
