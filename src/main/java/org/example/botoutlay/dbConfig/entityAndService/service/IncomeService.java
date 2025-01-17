package org.example.botoutlay.dbConfig.entityAndService.service;

import lombok.RequiredArgsConstructor;
import org.example.botoutlay.dbConfig.entityAndService.entity.Client;
import org.example.botoutlay.dbConfig.entityAndService.entity.Currency;
import org.example.botoutlay.dbConfig.entityAndService.entity.ServiceType;
import org.example.botoutlay.dbConfig.entityAndService.entity.Transaction;
import org.example.botoutlay.dbConfig.entityAndService.enums.CurrencyType;
import org.example.botoutlay.dbConfig.entityAndService.enums.TransactionType;
import org.example.botoutlay.dbConfig.entityAndService.mapper.IncomeMapper;
import org.example.botoutlay.dbConfig.entityAndService.payload.IncomeCreatDto;
import org.example.botoutlay.dbConfig.entityAndService.repository.ClientRepository;
import org.example.botoutlay.dbConfig.entityAndService.repository.CurrencyRepository;
import org.example.botoutlay.dbConfig.entityAndService.repository.ServiceTypeRepository;
import org.example.botoutlay.dbConfig.entityAndService.repository.TransactionRepository;
import org.example.botoutlay.dbConfig.exception.NotFoundException;
import org.example.botoutlay.dbConfig.response.ResponseData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncomeService {

    private final TransactionRepository transactionRepository;
    private final IncomeMapper incomeMapper;
    private final ClientRepository clientRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final CurrencyRepository currencyRepository;

    public ResponseData<?> creat(IncomeCreatDto incomeCreatDto) {
        Optional<Client> client = clientRepository.findByIdAndDeletedFalse(incomeCreatDto.getClientId());
        if (client.isEmpty()){
            throw new NotFoundException("Client not found");
        }
        Optional<ServiceType> serviceType = serviceTypeRepository.findByIdAndDeletedFalse(incomeCreatDto.getServiceTypeId());
        if (serviceType.isEmpty()){
            throw new NotFoundException("Service type not found");
        }
        if (incomeCreatDto.getAmount() < 0){
            return new ResponseData<>("The amount is enter incorrectly",false);
        }
        Optional<Currency> currencyOptional = this.currencyRepository.findByCcy(incomeCreatDto.getCurrencyType());
        if (currencyOptional.isEmpty()){
            throw new NotFoundException("Currency not found");
        }

        Transaction transaction = this.incomeMapper.toEntity(incomeCreatDto);
        transaction.setClient(client.get());
        transaction.setServiceType(serviceType.get());
        if (incomeCreatDto.getCurrencyType() != CurrencyType.UZS){
            transaction.setAmountUz(incomeCreatDto.getAmount()*currencyOptional.get().getRate());
            transaction.setCurrencyTypeUz(CurrencyType.UZS);
        }
        else {
            transaction.setAmountUz(incomeCreatDto.getAmount());
            transaction.setCurrencyTypeUz(CurrencyType.UZS);
        }
        if (incomeCreatDto.getDate() == null){
            transaction.setDate(LocalDate.now());
        }
        else {
            transaction.setDate(incomeCreatDto.getDate());
        }
        this.transactionRepository.save(transaction);
        return ResponseData.successResponse(this.incomeMapper.toDto(transaction));
    }

    public ResponseData<?> update(Long incomeId, IncomeCreatDto incomeCreatDto) {
        Optional<Transaction> transactionOptional = this.transactionRepository.findByIdAndDeletedFalse(incomeId);
        if (transactionOptional.isEmpty()){
            throw new NotFoundException("Transaction not found");
        }
        Optional<ServiceType> serviceType = serviceTypeRepository.findByIdAndDeletedFalse(incomeCreatDto.getServiceTypeId());
        if (serviceType.isEmpty()){
            throw new NotFoundException("Service type not found");
        }
        Optional<Client> client = clientRepository.findByIdAndDeletedFalse(incomeCreatDto.getClientId());
        if (client.isEmpty()){
            throw new NotFoundException("Client not found");
        }
        if (incomeCreatDto.getAmount() < 0){
            return new ResponseData<>("The amount is enter incorrectly",false);
        }
        Optional<Currency> currencyOptional = this.currencyRepository.findByCcy(incomeCreatDto.getCurrencyType());
        if (currencyOptional.isEmpty()){
            throw new NotFoundException("Currency not found");
        }

        Transaction transaction = this.incomeMapper.updateEntity(transactionOptional.get(), incomeCreatDto);
        if (incomeCreatDto.getDate() == null){
            transaction.setDate(LocalDate.now());
        }
        else {
            transaction.setDate(incomeCreatDto.getDate());
        }
        if (incomeCreatDto.getCurrencyType() != CurrencyType.UZS){
            transaction.setAmountUz(incomeCreatDto.getAmount()*currencyOptional.get().getRate());
            transaction.setCurrencyTypeUz(CurrencyType.UZS);
        }
        else {
            transaction.setAmountUz(incomeCreatDto.getAmount());
            transaction.setCurrencyTypeUz(CurrencyType.UZS);
        }
        transaction.setClient(client.get());
        transaction.setServiceType(serviceType.get());
        this.transactionRepository.save(transaction);
        return ResponseData.successResponse(this.incomeMapper.toDto(transaction));
    }

    public ResponseData<?> get(Long incomeId) {
        Optional<Transaction> transactionOptional = this.transactionRepository
                .findByIdAndDeletedFalse(incomeId);
        if (transactionOptional.isEmpty()){
            throw new NotFoundException("Transaction not found");
        }
        return ResponseData.successResponse(this.incomeMapper.toDto(transactionOptional.get()));
    }

    public ResponseData<?> delete(Long incomeId) {
        Optional<Transaction> transactionOptional = this.transactionRepository.findByIdAndDeletedFalse(incomeId);
        if (transactionOptional.isEmpty()){
            throw new NotFoundException("Transaction not found!");
        }
        Transaction transaction = transactionOptional.get();
        transaction.setDeleted(true);
        this.transactionRepository.save(transaction);
        return ResponseData.successResponse("success");
    }

    public ResponseData<?> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Transaction> transactions = this.transactionRepository.findAllByDeletedFalseAndTransactionType(pageable, TransactionType.EXPENSE);
        if (transactions.isEmpty()) {
            throw new NotFoundException("Transactions not found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", incomeMapper.toDto(transactions.toList()));
        response.put("totalPages", transactions.getTotalPages());
        response.put("total", transactions.getTotalElements());

        return ResponseData.successResponse(response);
    }
}
