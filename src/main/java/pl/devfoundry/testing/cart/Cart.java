package pl.devfoundry.testing.cart;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;
import pl.devfoundry.testing.meal.Meal;
import pl.devfoundry.testing.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Data
@Builder
public class Cart {

    @Singular
    private List<Order> orders;

    public void addOrderToCart(Cart cart, Order order) {
        final List<Order> collection = Stream.concat(orders.stream(), Stream.of(order)).collect(Collectors.toUnmodifiableList());
        cart.setOrders(collection);
    }

    public void clearCart(Cart cart) {
        cart.setOrders(new ArrayList<>());
    }

    public void simulateLargeOrder() {
        final Cart cart = Cart.builder().build();
        for (int i = 0; i < 1000; i++) {
            final Meal meal = new Meal(i % 10, 1, "Hamburger no " + i);
            final Order order = Order.builder().build();
            order.addMealToOrder(order, meal);
            addOrderToCart(cart, order);
        }
        log.info("Cart size: " + orders.size());
        clearCart(cart);
    }

}
