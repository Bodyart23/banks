package com.Sigma.banks.service;

import com.Sigma.banks.model.Banks;
import com.Sigma.banks.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankRepository bankRepository;
    private BankService bankService;

    public void setBankRepository(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public List<List<Banks>> getStatistics() {
        ArrayList<String> banks = bankRepository.getDistinctCurrency();

        ArrayList<List<Banks>> bestPurchase = new ArrayList<>();
        for (String currency : banks) {
            bestPurchase.add(bankRepository.getBestPurchase(currency));
        }

        ArrayList<List<Banks>> bestSale = new ArrayList<>();
        for (String currency : banks) {
            bestSale.add(bankRepository.getBestSale(currency));
        }

        List<List<Banks>> bestOffer = new ArrayList<>(bestPurchase);
        bestOffer.addAll(bestSale);
        return bestOffer;
    }

    @Override
    public List<Banks> retrieveBanks() {
        List<Banks> banks = bankRepository.findAll();
        return banks;
    }

    @Override
    public Banks getBanks(Long id) {
        Optional<Banks> banks = bankRepository.findById(id);
        return banks.get();
    }

    @Override
    public Banks saveBanks(Banks banks) {
        bankRepository.save(banks);
        return banks;
    }

    @Override
    public void deleteBank(String bank) {
        bankRepository.deleteBanksByBank(bank);
    }

    @Override
    public ResponseEntity<Banks> updateBanks(Long id, Banks banks) {
        return bankRepository.findById(id).map(record -> {
            record.setBank(banks.getBank());
            record.setCurrency(banks.getCurrency());
            record.setPurchase(banks.getPurchase());
            record.setSale(banks.getSale());
            Banks updated = bankService.saveBanks(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public List<Banks> getAllPurchaceCurrency(String currency) {
        List<Banks> banks = bankRepository.findAllPurchaseByCurrency(currency);
        return banks;
    }

    @Override
    public List<Banks> getAllSaleCurrency(String currency) {
        List<Banks> banks = bankRepository.findAllSaleByCurrency(currency);
        return banks;
    }
}
