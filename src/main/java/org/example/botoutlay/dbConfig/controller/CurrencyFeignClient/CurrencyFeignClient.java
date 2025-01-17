package org.example.botoutlay.dbConfig.controller.CurrencyFeignClient;

import org.example.botoutlay.dbConfig.entityAndService.enums.CurrencyType;
import org.example.botoutlay.dbConfig.entityAndService.payload.CurrencyUpdateDtoFromClientAPI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(name = "currencyFeignClient", url = "https://cbu.uz/uz/arkhiv-kursov-valyut")
public interface CurrencyFeignClient {

    @GetMapping("json/{ccy}/")
    List<CurrencyUpdateDtoFromClientAPI> getCcy(@PathVariable("ccy") CurrencyType ccy);

}
