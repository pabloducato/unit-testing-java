package pl.devfoundry.testing.account;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

class AccountServiceStubTest {

    // STUB'Y -> STUBS

    @Test
    void getAllActiveAccounts() {
        // given
        final AccountRepositoryStub accountRepositoryStub = new AccountRepositoryStub();
        final AccountService accountService = new AccountService(accountRepositoryStub);

        // when
        List<Account> accountList = accountService.getAllActiveAccounts();

        // then
        assertThat(accountList, hasSize(2));
    }

}
