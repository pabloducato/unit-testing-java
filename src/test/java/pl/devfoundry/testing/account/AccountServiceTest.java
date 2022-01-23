package pl.devfoundry.testing.account;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AccountServiceTest {

    @Test
    void getAllActiveAccounts() {
        // given
        List<Account> accounts = prepareAccountData();
        final AccountRepository accountRepository = mock(AccountRepository.class);
        final AccountService accountService = new AccountService(accountRepository);
        // when(accountRepository.getAllAccounts()).thenReturn(accounts);
        given(accountRepository.getAllAccounts()).willReturn(accounts);

        // when
        List<Account> accountList = accountService.getAllActiveAccounts();

        // then
        assertThat(accountList, hasSize(2));
    }

    @Test
    void getNoActiveAccounts() {
        // given
        final AccountRepository accountRepository = mock(AccountRepository.class);
        final AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(List.of());

        // when
        List<Account> accountList = accountService.getAllActiveAccounts();

        // then
        assertThat(accountList, hasSize(0));
    }

    private List<Account> prepareAccountData() {
        final Address address1 = new Address("Kwiatowa", "33/5");
        final Address address2 = new Address("Piekarska", "12b");
        final Account account1 = new Account(address1);
        final Account account2 = new Account();
        final Account account3 = new Account(address2);
        return Arrays.asList(account1, account2, account3);
    }

}
