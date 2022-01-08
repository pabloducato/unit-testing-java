package pl.devfoundry.testing;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
@Slf4j
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
            final Meal meal = new Meal(i % 10, "Hamburger no " + i);
            final Order order = Order.builder().build();
            order.addMealToOrder(order, meal);
            addOrderToCart(cart, order);
        }
        log.info("Cart size: " + orders.size());
        clearCart(cart);
    }

}
