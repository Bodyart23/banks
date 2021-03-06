package com.banks.repository;

import com.banks.model.Banks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

@EnableJpaRepositories("com.Sigma.banks.repository")
public interface BankRepository extends JpaRepository<Banks, Long> {
    @Query("select u.bank,u.currency,u.purchase from Banks u where u.currency=:currency")
    List<Banks> findAllPurchaseByCurrency(@Param("currency") String currency);
    @Query("select u.bank,u.currency,u.sale from Banks u where u.currency=:currency")
    List<Banks> findAllSaleByCurrency(@Param("currency") String currency);
    @Query("delete from Banks u where u.bank = :bank")
    void deleteBanksByBank(@Param("bank") String bank);
    @Query(value = "insert into banks (bank, currency, purchase, sale) VALUE (?,?,?,?)",nativeQuery = true)
    Banks save(Banks banks);
    @Query("select distinct u.currency from Banks u")
    ArrayList<String> getDistinctCurrency();
    @Query("select u.bank,u.currency,u.purchase from Banks u where u.purchase = (select min (a.purchase) from Banks a where a.currency = :currency)")
    List<Banks> getBestPurchase(@Param("currency") String currency);
    @Query("select i.bank,i.currency,i.sale from Banks i where i.sale = (select max (b.sale) from Banks b where b.currency = :currency)")
    List<Banks> getBestSale(@Param("currency") String currency);
}
