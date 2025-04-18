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
        //Optional<Account> optionalAccount = Optional.of(accountRepository.findByUsername(account.getUsername()));//check if the username exists
        boolean isDuplicate = (accountRepository.findByUsername(account.getUsername()) != null);
        if (isDuplicate || account.getUsername() == "" || account.getPassword().length() < 4){
            return null;
        }
        accountRepository.save(account);
        return accountRepository.findByUsername(account.getUsername());
    }

    public Account getAccount(Account account){
        String username = account.getUsername();
        String password = account.getPassword();



        Account foundAccount = accountRepository.findByUsername(account.getUsername());
        boolean isNotFound = (foundAccount == null);

        // if (foundAccount!=null){
        //     String foundPass = foundAccount.getPassword();
        //     if (password.equals)
        // }

        // return null;

        if (isNotFound || account.getUsername() == "" || account.getPassword().length() < 4 || !(foundAccount.getPassword().equals(account.getPassword()))){
            System.out.println("hi");
            return null;
        }

        return foundAccount; //accountRepository.save(account);
    }
    // public Account getAccountById(Integer id){ //broke?
    //     Optional<Account> optionalAccount = accountRepository.findById(id);
    //     if (optionalAccount.isPresent()){
    //         return optionalAccount.get();
    //     }
    //     return null;
    // }
    public boolean isAccountInDb(Integer id){
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()){
            return true;
        }
        return false;
    }
}
