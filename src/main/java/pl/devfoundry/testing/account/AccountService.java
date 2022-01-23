package pl.devfoundry.testing.account;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    List<Account> getAllActiveAccounts() {
        return accountRepository.getAllAccounts().stream()
                .filter(Account::isActive)
                .collect(Collectors.toList());
    }

}
