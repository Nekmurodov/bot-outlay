package org.example.botoutlay.dbConfig.entityAndService.mapper;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.Client;
import org.example.botoutlay.dbConfig.entityAndService.payload.ClientCreatDto;
import org.example.botoutlay.dbConfig.entityAndService.payload.ClientDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    public Client toEntity(ClientCreatDto dto) {
        Client client = new Client();
        client.setFullName(dto.getFullName());
        client.setPhoneNumber(dto.getPhoneNumber());
        return client;
    }

    public ClientDto toDto(Client client) {
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setFullName(client.getFullName());
        dto.setPhoneNumber(client.getPhoneNumber());
        return dto;
    }

    public List<ClientDto> toDto(List<Client> clients) {
        List<ClientDto> clientDto = new ArrayList<>();
        for (Client client : clients) {
            clientDto.add(toDto(client));
        }
        return clientDto;
    }

    public Client toUpdate(ClientCreatDto clientDto, Client client) {
        if (clientDto.getFullName() != null) {
            client.setFullName(clientDto.getFullName());
        }
        if (clientDto.getPhoneNumber() != null) {
            client.setPhoneNumber(clientDto.getPhoneNumber());
        }
        return client;
    }

}
