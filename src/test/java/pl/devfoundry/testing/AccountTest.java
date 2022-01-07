package pl.devfoundry.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

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
        assertThat(account.isActive(), equalTo(false));
        assertThat(account.isActive(), is(false));
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
        assertThat(account.isActive(), equalTo(true));
        assertThat(account.isActive(), is(true));
    }

    @Test
    @SuppressWarnings("all")
    void test_newlyCreatedAccountShouldNotHaveDefaultDeliveryAddress() {
        // given
        final Account account = new Account();

        // when
        final Address address = account.getDefaultDeliveryAddress();

        // then
        assertNull(address);
        assertThat(address, equalTo(null));
        assertThat(address, nullValue());
    }

    @Test
    void test_defaultDeliveryAddressShouldNotBeNulAfterBeingSet() {
        // given
        final Address address = new Address("Krakowska", "67c");
        final Account account = new Account();
        account.setDefaultDeliveryAddress(address);

        // when
        final Address testedAddress = account.getDefaultDeliveryAddress();

        // then
        assertNotNull(testedAddress);
        assertEquals(address, testedAddress);
        assertThat(testedAddress, equalTo(address));
        assertThat(testedAddress, is(address));
    }

}
