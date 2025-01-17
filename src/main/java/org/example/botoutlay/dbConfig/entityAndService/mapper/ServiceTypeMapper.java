package org.example.botoutlay.dbConfig.entityAndService.mapper;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.ServiceType;
import org.example.botoutlay.dbConfig.entityAndService.payload.ServiceTypeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ServiceTypeMapper {

    public ServiceTypeDto toDto(ServiceType serviceType) {
        ServiceTypeDto dto = new ServiceTypeDto();
        dto.setId(serviceType.getId());
        dto.setName(serviceType.getName());
        return dto;
    }

    public List<ServiceTypeDto> toDto(List<ServiceType> serviceTypes) {
        List<ServiceTypeDto> serviceTypeDto = new ArrayList<>();
        for (ServiceType serviceType : serviceTypes) {
            serviceTypeDto.add(toDto(serviceType));
        }
        return serviceTypeDto;
    }

}
