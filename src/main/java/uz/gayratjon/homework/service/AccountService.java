package uz.gayratjon.homework.service;

import org.springframework.stereotype.Service;
import uz.gayratjon.homework.entity.Account;
import uz.gayratjon.homework.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //post  save
    public Account save(Account account){
        return accountRepository.save(account);
    }

    //delete
    public void delete(Long id){
        accountRepository.deleteById(id);
    }

}
