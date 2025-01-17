package org.example.botoutlay.dbConfig.entityAndService.service;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.ServiceType;
import org.example.botoutlay.dbConfig.entityAndService.mapper.ServiceTypeMapper;
import org.example.botoutlay.dbConfig.entityAndService.payload.ServiceTypeCreatDto;
import org.example.botoutlay.dbConfig.entityAndService.repository.ServiceTypeRepository;
import org.example.botoutlay.dbConfig.exception.AlreadyExistException;
import org.example.botoutlay.dbConfig.exception.NotFoundException;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceTypeMapper serviceTypeMapper;

    public ResponseData<?> creat(ServiceTypeCreatDto serviceTypeCreatDto) {
        if (this.serviceTypeRepository.existsByNameAndDeletedFalse(serviceTypeCreatDto.getName())) {
            throw new AlreadyExistException("Service type already exists");
        }
        ServiceType serviceType = new ServiceType();
        serviceType.setName(serviceTypeCreatDto.getName());
        this.serviceTypeRepository.save(serviceType);
        return ResponseData.successResponse("Service type created");
    }

    public ResponseData<?> update(Long serviceId, ServiceTypeCreatDto serviceTypeCreatDto) {
        Optional<ServiceType> serviceTypeOptional = this.serviceTypeRepository.findByIdAndDeletedFalse(serviceId);
        if (serviceTypeOptional.isEmpty()){
            throw new NotFoundException("Service type not found");
        }
        if (this.serviceTypeRepository.existsByNameAndDeletedFalse(serviceTypeCreatDto.getName())) {
            throw new AlreadyExistException("Service type already exists");
        }
        ServiceType serviceType = new ServiceType();
        serviceType.setName(serviceTypeCreatDto.getName());
        this.serviceTypeRepository.save(serviceType);
        return ResponseData.successResponse("Service type updated");
    }

    public ResponseData<?> get(Long serviceId) {
        Optional<ServiceType> serviceTypeOptional = this.serviceTypeRepository.findByIdAndDeletedFalse(serviceId);
        if (serviceTypeOptional.isEmpty()){
            throw new NotFoundException("Service type not found");
        }
        return ResponseData.successResponse(this.serviceTypeMapper.toDto(serviceTypeOptional.get()));
    }

    public ResponseData<?> getAll() {
        List<ServiceType> serviceTypeOptional = this.serviceTypeRepository.findAllByDeletedFalse();
        if (serviceTypeOptional.isEmpty()){
            throw new NotFoundException("Service type not found");
        }
        return ResponseData.successResponse(this.serviceTypeMapper.toDto(serviceTypeOptional));
    }

    public ResponseData<?> delete(Long serviceId) {
        Optional<ServiceType> serviceTypeOptional = this.serviceTypeRepository.findByIdAndDeletedFalse(serviceId);
        if (serviceTypeOptional.isEmpty()){
            throw new NotFoundException("Service type not found");
        }
        ServiceType serviceType = serviceTypeOptional.get();
        serviceType.setDeleted(true);
        this.serviceTypeRepository.save(serviceType);
        return ResponseData.successResponse("Service type deleted");
    }

}
