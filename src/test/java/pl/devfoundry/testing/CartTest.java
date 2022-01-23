package pl.devfoundry.testing;

import org.junit.jupiter.api.*;
import pl.devfoundry.testing.order.Order;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@Tag("test")
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

    @Test
    void test_cartShouldNotBeEmptyAfterAddingOrderToCart() {
        // given
        final pl.devfoundry.testing.order.Order order = pl.devfoundry.testing.order.Order.builder().build();
        final Cart cart = Cart.builder().build();

        // when
        cart.addOrderToCart(cart, order);

        // then
        // alternative
        assertThat(cart.getOrders(), anyOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(pl.devfoundry.testing.order.Order.class)))
        ));

        // conjunction
        assertThat(cart.getOrders(), allOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(pl.devfoundry.testing.order.Order.class)))
        ));

        // assertAll matcher
        assertAll("This is a group of assertions for cart",
                () -> assertThat(cart.getOrders(), notNullValue()),
                () -> assertThat(cart.getOrders(), hasSize(1)),
                () -> assertThat(cart.getOrders(), is(not(empty()))),
                () -> assertThat(cart.getOrders(), is(not(emptyCollectionOf(Order.class)))),
                () -> {
                    final List<Meal> meals = cart.getOrders().get(0).getMeals();
                    assertThat(meals, empty());
                }
        );
    }

}
