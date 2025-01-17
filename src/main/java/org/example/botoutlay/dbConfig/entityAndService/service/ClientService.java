package org.example.botoutlay.dbConfig.entityAndService.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.Client;
import org.example.botoutlay.dbConfig.entityAndService.entity.ServiceType;
import org.example.botoutlay.dbConfig.entityAndService.mapper.ClientMapper;
import org.example.botoutlay.dbConfig.entityAndService.payload.ClientCreatDto;
import org.example.botoutlay.dbConfig.entityAndService.repository.ClientRepository;
import org.example.botoutlay.dbConfig.entityAndService.repository.ServiceTypeRepository;
import org.example.botoutlay.dbConfig.exception.NotFoundException;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ServiceTypeRepository serviceTypeRepository;

    public ResponseData<?> creat(ClientCreatDto clientCreatDto) {
        Client client = clientMapper.toEntity(clientCreatDto);
        if (clientCreatDto.getServiceTypeId()!=null){
            Optional<ServiceType> serviceType = serviceTypeRepository.
                    findByIdAndDeletedFalse(clientCreatDto.getServiceTypeId());
              if (serviceType.isPresent()){
                  client.setServiceType(serviceType.get());
              } else {
                  throw new EntityNotFoundException("Service type not found");
              }
        }
        clientRepository.save(client);
        return ResponseData.successResponse(this.clientMapper.toDto(client));
    }

    public ResponseData<?> update(Long clientId, ClientCreatDto clientDto) {
        Optional<Client> clientOptional = clientRepository.findByIdAndDeletedFalse(clientId);
        if (clientOptional.isEmpty()) {
            throw new EntityNotFoundException("Client with id " + clientId + " not found");
        }
        Client client = this.clientMapper.toUpdate(clientDto, clientOptional.get());
        if (clientDto.getServiceTypeId()!=null){
            Optional<ServiceType> serviceType = serviceTypeRepository.
                    findByIdAndDeletedFalse(clientDto.getServiceTypeId());
            if (serviceType.isPresent()){
                client.setServiceType(serviceType.get());
            } else {
                throw new EntityNotFoundException("Service type not found!");
            }
        }
        clientRepository.save(client);
        return ResponseData.successResponse(this.clientMapper.toDto(client));
    }

    public ResponseData<?> get(Long clientId) {
        Optional<Client> clientOptional = clientRepository.findByIdAndDeletedFalse(clientId);
        if (clientOptional.isEmpty()) {
            throw new EntityNotFoundException("Client with id " + clientId + " not found");
        }
        return ResponseData.successResponse(this.clientMapper.toDto(clientOptional.get()));
    }

    public ResponseData<?> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Client> client = this.clientRepository.findAllByDeletedFalse(pageable);
        if (client.isEmpty()) {
            throw new NotFoundException("Clients not found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", clientMapper.toDto(client.toList()));
        response.put("total", client.getTotalElements());
        response.put("totalPages", client.getTotalPages());

        return ResponseData.successResponse(response);
    }

    public ResponseData<?> delete(Long clientId) {
        Optional<Client> clientOptional = clientRepository.findByIdAndDeletedFalse(clientId);
        if (clientOptional.isEmpty()) {
            throw new EntityNotFoundException("Client with id " + clientId + " not found");
        }
        Client client = clientOptional.get();
        client.setDeleted(true);
        clientRepository.save(client);
        return ResponseData.successResponse("success");
    }

}
