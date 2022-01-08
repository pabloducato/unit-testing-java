package pl.devfoundry.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

class CartTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_simulateLargeOrder() {
        // given
        final Cart cart = Cart.builder().build();

        // when // then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrder);
    }
    
}
