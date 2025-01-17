package org.example.botoutlay.dbConfig.entityAndService.payload;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyUpdateDtoFromClientAPI {

    @JsonProperty("Ccy")
    private String ccy;

    @JsonProperty("CcyNm_RU")
    private String ccyNmRU;

    @JsonProperty("CcyNm_UZ")
    private String ccyNmUZ;

    @JsonProperty("CcyNm_EN")
    private String ccyNmEN;

    @JsonProperty("Rate")
    private String rate;


}
