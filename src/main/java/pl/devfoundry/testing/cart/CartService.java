package pl.devfoundry.testing.cart;

import lombok.RequiredArgsConstructor;
import pl.devfoundry.testing.order.OrderStatus;

@RequiredArgsConstructor
public class CartService {

    private final CartHandler cartHandler;

    Cart processCart(Cart cart) {
        if (cartHandler.canHandleCart(cart)) {
            cartHandler.sendToPrepare(cart);
            cart.getOrders().forEach(order -> order.changeOrderStatus(OrderStatus.PREPARING));
            return cart;
        } else {
            cart.getOrders().forEach(order -> order.changeOrderStatus(OrderStatus.REJECTED));
            return cart;
        }
    }

}
