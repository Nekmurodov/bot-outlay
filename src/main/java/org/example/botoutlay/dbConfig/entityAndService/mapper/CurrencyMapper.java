package org.example.botoutlay.dbConfig.entityAndService.mapper;


import org.example.botoutlay.dbConfig.entityAndService.entity.Currency;
import org.example.botoutlay.dbConfig.entityAndService.payload.CurrencyUpdateDtoFromClientAPI;

public class CurrencyMapper {

    public static void fromUpdateDto(CurrencyUpdateDtoFromClientAPI updateDto, Currency currency) {
        if ( updateDto == null ) {
            return;
        }
        if ( updateDto.getCcyNmUZ() != null ) {
            currency.setCcyNmUz( updateDto.getCcyNmUZ() );
        }
        if ( updateDto.getCcyNmRU() != null ) {
            currency.setCcyNmRu( updateDto.getCcyNmRU() );
        }
        if ( updateDto.getCcyNmEN() != null ) {
            currency.setCcyNumEn( updateDto.getCcyNmEN() );
        }
        if ( updateDto.getRate() != null ) {
            currency.setRate(Double.parseDouble(updateDto.getRate()));
        }
    }
}
