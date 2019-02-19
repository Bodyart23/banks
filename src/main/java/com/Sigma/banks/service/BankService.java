package com.Sigma.banks.service;

import com.Sigma.banks.model.Banks;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface BankService {
    public List<Banks> retrieveBanks();

    public Banks getBanks(Long id);

    public Banks saveBanks(Banks banks);

    public void deleteBank(String bank);

    public ResponseEntity<Banks> updateBanks(Long id, Banks banks);

    public List<Banks> getAllPurchaceCurrency(String currency);
    public List<Banks> getAllSaleCurrency(String currency);
    public ArrayList<ArrayList<Banks>> getTheBest();
}
