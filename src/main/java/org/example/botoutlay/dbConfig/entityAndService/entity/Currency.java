package org.example.botoutlay.dbConfig.entityAndService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.abs.AbsEntity;
import org.example.botoutlay.dbConfig.entityAndService.enums.CurrencyType;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency extends AbsEntity {

    @JsonProperty("Ccy")
    @Enumerated(EnumType.STRING)
    private CurrencyType ccy;

    @JsonProperty("CcyNm_UZ")
    private String ccyNmUz; //AQSH DOLLARI

    @JsonProperty("CcyNm_RU")
    private String ccyNmRu;

    @JsonProperty("CcyNm_EN")
    private String ccyNumEn;

    @JsonProperty("Rate")
    private Double rate; //12550


}
