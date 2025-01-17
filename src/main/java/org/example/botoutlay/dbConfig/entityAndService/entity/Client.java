package org.example.botoutlay.dbConfig.entityAndService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.botoutlay.dbConfig.entityAndService.abs.AbsEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client extends AbsEntity {

    private String fullName;

    private String phoneNumber;

    @ManyToOne
    private ServiceType serviceType;
}
