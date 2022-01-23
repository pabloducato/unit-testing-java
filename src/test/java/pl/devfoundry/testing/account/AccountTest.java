package pl.devfoundry.testing.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import pl.devfoundry.testing.account.Account;
import pl.devfoundry.testing.account.Address;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@Tag("test")
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

        // hamcrest
        assertThat(account.isActive(), equalTo(false));
        assertThat(account.isActive(), is(false));

        // assertJ
        Assertions.assertThat(account.isActive()).isFalse();
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

        // hamcrest
        assertThat(account.isActive(), equalTo(true));
        assertThat(account.isActive(), is(true));

        // assertJ
        Assertions.assertThat(account.isActive()).isTrue();
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

        // assertJ
        Assertions.assertThat(address).isNull();

        // hamcrest
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

        // assertJ
        Assertions.assertThat(testedAddress).isNotNull();

        // hamcrest
        assertThat(testedAddress, notNullValue());
        assertThat(testedAddress, is(notNullValue()));
        assertEquals(address, testedAddress);

        // hamcrest
        assertThat(testedAddress, equalTo(address));
        assertThat(testedAddress, is(address));
    }

    @Test
    @SuppressWarnings("all")
    void test_newAccountWithNotNullAddressShouldBeActive() {
        // given
        final Address address = new Address("Puławska", "46/6");

        // when
        final Account account = new Account(address);

        // then
        // functional interface Executable - lambda expression
        assumingThat(address != null, () -> {
            assertTrue(account.isActive());
        });
    }

    @Test
    @SuppressWarnings("all")
    @RepeatedTest(5)
    void test_newAccountWithNullAddressShouldBeInactive() {
        // given
        final Address address = new Address();

        // when
        final Account account = new Account(address);

        // then
        // functional interface Executable - lambda expression
        assumingThat(address == null, () -> {
            assertFalse(account.isActive());
        });
    }

    @Test
    void test_invalidEmailShouldThrowException() {
        // given
        final Account account = new Account();

        // when // then
        assertThrows(IllegalArgumentException.class, () -> account.setEmail("wrongEmail"));
    }

    @Test
    void test_validEmailShouldBeSet() {
        // given
        final Account account = new Account();
        final String email = "kontakt@devfoundry.pl";

        // when
        account.setEmail(email);

        // then
        assertThat(account.getEmail(), is(email));
    }
}
