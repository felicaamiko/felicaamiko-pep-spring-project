package com.example.service;

import com.example.repository.AccountRepository;
import com.example.entity.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account){
        Optional<Account> optionalAccount = accountRepository.findById(account.getAccountId());
        if (optionalAccount.isPresent() || account.getUsername() == "" || account.getPassword().length() < 4){
            return null;
        }
        accountRepository.save(account);
        return accountRepository.findByUsername(account.getUsername());
    }

    public Account getAccountById(Integer id){
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()){
            return optionalAccount.get();
        }
        return null;
    }


}
