package org.example.botoutlay.dbConfig.entityAndService.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.example.botoutlay.dbConfig.entityAndService.abs.AbsEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceType extends AbsEntity {
    private String name;
}
