package com.Sigma.banks.service;

import com.Sigma.banks.model.Banks;

import java.util.List;

public interface BankService {
    public List<Banks> retrieveBanks();

    public Banks getBanks(Long id);

    public Banks saveBanks(Banks banks);

    public void deleteBank(String bank);

    public void updateBanks(Banks banks);

    public List<Banks> getAllPurchaceCurrency(String currency);
    public List<Banks> getAllSaleCurrency(String currency);
}
