package org.example.botoutlay.dbConfig.entityAndService.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientCreatDto {
    private String fullName;
    private String phoneNumber;
    private Long serviceTypeId;

}
