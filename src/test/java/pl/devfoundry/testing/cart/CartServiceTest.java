package pl.devfoundry.testing.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.devfoundry.testing.order.Order;
import pl.devfoundry.testing.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @InjectMocks
    private CartService cartService;
    @Mock
    private CartHandler cartHandler;
    @Captor
    private ArgumentCaptor<Cart> argumentCaptor;

    @Test
    void processCartShouldSendToPrepare() {

        // given
        final Order order = Order.builder().build();
        final Cart cart = Cart.builder().build();
        cart.addOrderToCart(cart, order);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        // when
        Cart resultCart = cartService.processCart(cart);

        // then
        verify(cartHandler).sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(cart);

        verify(cartHandler, atLeastOnce()).sendToPrepare(cart);

        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void processCartShouldNotSendToPrepare() {

        // given
        final Order order = Order.builder().build();
        final Cart cart = Cart.builder().build();
        cart.addOrderToCart(cart, order);

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        // when
        Cart resultCart = cartService.processCart(cart);

        // then
        verify(cartHandler, never()).sendToPrepare(cart);
        then(cartHandler).should(never()).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void processCartShouldNotSendToPrepareWithArgumentMatches() {

        // given
        final Order order = Order.builder().build();
        final Cart cart = Cart.builder().build();
        cart.addOrderToCart(cart, order);

        // any() - argument matcher z Mockito
        // bardzo og??lny matcher, kt??ry ??apie ka??d?? warto????
        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(false);

        // when
        Cart resultCart = cartService.processCart(cart);

        // then
        verify(cartHandler, never()).sendToPrepare(any(Cart.class));
        then(cartHandler).should(never()).sendToPrepare(any(Cart.class));

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void canHandleCartShouldReturnMultipleValues() {

        // given  // when
        final Order order = Order.builder().build();
        final Cart cart = Cart.builder().build();
        cart.addOrderToCart(cart, order);

        // any() - argument matcher z Mockito
        // bardzo og??lny matcher, kt??ry ??apie ka??d?? warto????
        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(true, false, false, true);

        // then
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
    }

    @Test
    void processCartShouldSendToPrepareWithLambdas() {

        // given
        final Order order = Order.builder().build();
        final Cart cart = Cart.builder().build();
        cart.addOrderToCart(cart, order);

        given(cartHandler.canHandleCart(argThat(c -> c.getOrders().size() > 0))).willReturn(true);

        // when
        Cart resultCart = cartService.processCart(cart);

        // then
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void canHandleCarShouldThrowException() {

        // given
        final Order order = Order.builder().build();
        final Cart cart = Cart.builder().build();
        cart.addOrderToCart(cart, order);

        given(cartHandler.canHandleCart(cart)).willThrow(IllegalStateException.class);

        // when // then
        assertThrows(IllegalStateException.class, () -> cartService.processCart(cart));
    }

    // Argument Captor test case

    @Test
    void processCartShouldSendToPrepareWithArgumentCaptor() {

        // given
        final Order order = Order.builder().build();
        final Cart cart = Cart.builder().build();
        cart.addOrderToCart(cart, order);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        // when
        Cart resultCart = cartService.processCart(cart);

        // then
        //verify(cartHandler).sendToPrepare(argumentCaptor.capture());
        then(cartHandler).should().sendToPrepare(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue().getOrders().size(), equalTo(1));

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void shouldDoNothingWhenProcessCart() {

        // given
        final Order order = Order.builder().build();
        final Cart cart = Cart.builder().build();
        cart.addOrderToCart(cart, order);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

//        doNothing().when(cartHandler).sendToPrepare(cart);
//        willDoNothing().given(cartHandler).sendToPrepare(cart);
//        willDoNothing().willThrow(IllegalStateException.class).given(cartHandler).sendToPrepare(cart);

        // when
        Cart resultCart = cartService.processCart(cart);

        // then
        then(cartHandler).should().sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void shouldAnswerWhenProcessCart() {

        // given
        final Order order = Order.builder().build();
        final Cart cart = Cart.builder().build();
        cart.addOrderToCart(cart, order);

        doAnswer(invocationOnMock -> {
            Cart argumentCart = invocationOnMock.getArgument(0);
            argumentCart.clearCart(cart);
            return true;
        }).when(cartHandler).canHandleCart(cart);

//        when(cartHandler.canHandleCart(cart)).then(i -> {
//            Cart argumentCart = i.getArgument(0);
//            argumentCart.clearCart(cart);
//            return true;
//        });

        // alternatywny zapis, kt??ry jest bardziej przyjazny dla podej??cia BDD

//        willAnswer(invocationOnMock -> {
//            Cart argumentCart = invocationOnMock.getArgument(0);
//            argumentCart.clearCart(cart);
//            return true;
//        }).given(cartHandler).canHandleCart(cart);
//
//        given(cartHandler.canHandleCart(cart)).will(i -> {
//            Cart argumentCart = i.getArgument(0);
//            argumentCart.clearCart(cart);
//            return true;
//        });

        // when
        Cart resultCart = cartService.processCart(cart);

        // then
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(resultCart.getOrders().size(), equalTo(0));
    }

    @Test
    void deliveryShouldBeFree() {
        // given
        final Cart cart = Cart.builder().build();
        cart.addOrderToCart(cart, Order.builder().build());
        cart.addOrderToCart(cart, Order.builder().build());
        cart.addOrderToCart(cart, Order.builder().build());

        doCallRealMethod().when(cartHandler).isDeliveryFree(cart);
        //given(cartHandler.isDeliveryFree(cart)).willCallRealMethod();

        // when
        boolean isDeliveryFree = cartHandler.isDeliveryFree(cart);

        // then
        assertTrue(isDeliveryFree);
    }

}
