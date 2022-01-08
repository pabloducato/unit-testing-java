package pl.devfoundry.testing;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("Test cases for Cart")
class CartTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Cart is able to process 1000 orders in 100 ms")
    void test_simulateLargeOrder() {
        // given
        final Cart cart = Cart.builder().build();

        // when // then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrder);
    }

    @Disabled
    @Test
    @SuppressWarnings("all")
    void test_disabledTest() {
        // given
        // when
        // then
    }

}
