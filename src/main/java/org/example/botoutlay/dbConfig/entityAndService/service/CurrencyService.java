package org.example.botoutlay.dbConfig.entityAndService.service;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.controller.CurrencyFeignClient.CurrencyFeignClient;
import org.example.botoutlay.dbConfig.entityAndService.entity.Currency;
import org.example.botoutlay.dbConfig.entityAndService.enums.CurrencyType;
import org.example.botoutlay.dbConfig.entityAndService.mapper.CurrencyMapper;
import org.example.botoutlay.dbConfig.entityAndService.payload.CurrencyUpdateDtoFromClientAPI;
import org.example.botoutlay.dbConfig.entityAndService.repository.CurrencyRepository;
import org.example.botoutlay.dbConfig.exception.NotFoundException;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyFeignClient currencyClient;
    private final CurrencyRepository currencyRepository;

    @Scheduled(fixedRate = 18000000) // per 5 hours
    public void scheduledMethod() {
        CurrencyUpdateDtoFromClientAPI usd = currencyClient.getCcy(CurrencyType.USD).get(0);
        updateCurrency(usd, CurrencyType.USD);

        CurrencyUpdateDtoFromClientAPI  rub = currencyClient.getCcy(CurrencyType.RUB).get(0);
        updateCurrency(rub, CurrencyType.RUB);

        CurrencyUpdateDtoFromClientAPI  eur = currencyClient.getCcy(CurrencyType.EUR).get(0);
        updateCurrency(eur, CurrencyType.EUR);

    }

    public ResponseData<?> getAll(){
        List<Currency> currencyList = currencyRepository.findAll();
        if (currencyList.isEmpty()) {
            throw new NotFoundException("Currency not found");
        }
        return ResponseData.successResponse(currencyList);
    }

    private void updateCurrency(CurrencyUpdateDtoFromClientAPI updateDto, CurrencyType ccy) {
        Optional<Currency> optionalCurrency = currencyRepository.findByCcy(ccy);
        if (optionalCurrency.isPresent()) {
           Currency currency = optionalCurrency.get();
           CurrencyMapper.fromUpdateDto(updateDto, currency);
           currencyRepository.save(currency);

        }
    }
}
