package pl.devfoundry.testing.account;

import java.util.Arrays;
import java.util.List;

public class AccountRepositoryStub implements AccountRepository {

    @Override
    public List<Account> getAllAccounts() {
        final Address address1 = new Address("Kwiatowa", "33/5");
        final Address address2 = new Address("Piekarska", "12b");
        final Account account1 = new Account(address1);
        final Account account2 = new Account();
        final Account account3 = new Account(address2);
        return Arrays.asList(account1, account2, account3);
    }

    //getByName zostawiamy tak jak jest, nie będziemy się tym przejmować

    @Override
    public List<String> getByName(String name) {
        return null;
    }
}
