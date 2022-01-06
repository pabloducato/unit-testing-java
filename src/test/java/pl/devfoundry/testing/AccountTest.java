package pl.devfoundry.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_checkWhetherNewAccountIsNotActive() {
        // given // when
        final Account account = new Account();
        // then
        assertFalse(account.isActive(), "Check whether new account is not active");
    }

    @Test
    void test_checkWhetherAccountWasActivated() {
        // given
        final Account account = new Account();
        assertFalse(account.isActive(), "Check whether new account is not active");

        // when
        account.activate();
        // then
        assertTrue(account.isActive(), "Check whether account was activated");
    }

}
