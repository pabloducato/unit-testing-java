package pl.devfoundry.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

@Tag("test")
class OrderStatusTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @ParameterizedTest
    @EnumSource(OrderStatus.class)
    void test_allOrderStatusShouldBeShorterThan15Chars(OrderStatus orderStatus) {
        // given // when // then
        assertThat(orderStatus.toString().length(), lessThan(15));
    }

}
