package com.Sigma.banks.controllers;

import com.Sigma.banks.model.Banks;
import com.Sigma.banks.repository.BankRepository;
import com.Sigma.banks.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    public BankService bankService;
    public BankRepository bankRepository;

    public void setBankService(BankService bankService){
        this.bankService = bankService;
    }

    @GetMapping("/api/banks")
    public List<Banks> getBank() {
        List<Banks> banks = bankService.retrieveBanks();
        return banks;
    }
    @GetMapping("/api/bank/{id}")
    public Banks getBanks(@PathVariable(name="id")Long id) {
        return bankService.getBanks(id);
    }
    @GetMapping("/api/banks/purchase/{currency}")
    public List<Banks> getAllPurchaceCurrency(@PathVariable(name = "currency") String currency) {
        List<Banks> banks = bankService.getAllPurchaceCurrency(currency);
        return banks;
    }
    @GetMapping("/api/banks/sale/{currency}")
    public List<Banks> getAllSaleCurrency(@PathVariable(name = "currency") String currency) {
        List<Banks> banks = bankService.getAllSaleCurrency(currency);
        return banks;
    }
    @PostMapping
    public Banks create(@RequestBody Banks banks){
        return bankService.saveBanks(banks);
    }
    @PutMapping(value = "/api/update/{id}")
    public ResponseEntity<Banks> update(@PathVariable("id")Long id, @RequestBody Banks banks){
        return bankRepository.findById(id).map(record->{
            record.setBank(banks.getBank());
            record.setCurrency(banks.getCurrency());
            record.setPurchase(banks.getPurchase());
            record.setSale(banks.getSale());
            Banks updated = bankService.saveBanks(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/api/delete/{bank}")
    public void deleteBank(@PathVariable(name="bank")String bank){
        bankService.deleteBank(bank);
    }

}
