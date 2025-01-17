package org.example.botoutlay.dbConfig.controller;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.payload.ServiceTypeCreatDto;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.example.botoutlay.dbConfig.entityAndService.service.ServiceTypeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/service-type/")
public class ServiceTypeController {
    private final ServiceTypeService serviceTypeService;

    @PostMapping("/creat")
    public ResponseData<?> creat(@RequestBody ServiceTypeCreatDto serviceTypeCreatDto) {
        return this.serviceTypeService.creat(serviceTypeCreatDto);
    }

    @PutMapping("update/{serviceId}")
    public ResponseData<?> update(@PathVariable Long serviceId, ServiceTypeCreatDto serviceTypeCreatDto) {
        return this.serviceTypeService.update(serviceId, serviceTypeCreatDto);
    }

    @GetMapping("/get/{serviceId}")
    public ResponseData<?> get(@PathVariable Long serviceId) {
        return this.serviceTypeService.get(serviceId);
    }

    @GetMapping("/get-all")
    public ResponseData<?> getAll() {
        return this.serviceTypeService.getAll();
    }

    @DeleteMapping("delete/{serviceId}")
    public ResponseData<?> delete(@PathVariable Long serviceId) {
        return this.serviceTypeService.delete(serviceId);
    }
}
