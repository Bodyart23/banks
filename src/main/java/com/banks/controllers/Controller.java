package com.banks.controllers;

import com.banks.model.Banks;
import com.banks.repository.BankRepository;
import com.banks.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    public BankService bankService;
    public BankRepository bankRepository;

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    @RequestMapping(value = "/api/banks", //
            method = RequestMethod.GET
//    produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<Banks> getAll() {
        List<Banks> banks = bankService.retrieveBanks();
        return banks;
    }

    @RequestMapping(value = "/api/banks/statistics", //
            method = RequestMethod.GET
//    produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<List<Banks>> getBank() {
        List<List<Banks>> banks = bankService.getStatistics();
        return banks;
    }

    @GetMapping("/api/bank/{id}")
    public Banks getBanks(@PathVariable(name = "id") Long id) {
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

    @RequestMapping(value = "/bank", //
            method = RequestMethod.POST, //
            consumes = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.TEXT_PLAIN_VALUE})
    @ResponseBody
    public Banks create(@RequestBody Banks banks) {
        return bankService.saveBanks(banks);
    }

    @PutMapping(value = "/api/update/{id}")
    public ResponseEntity<Banks> update(@PathVariable("id") Long id, @RequestBody Banks banks) {
        return bankService.updateBanks(id,banks);
    }

    @DeleteMapping("/api/delete/{bank}")
    public void deleteBank(@PathVariable(name = "bank") String bank) {
        bankService.deleteBank(bank);
    }

}
