package pl.devfoundry.testing;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class MealTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_shouldReturnDiscountedPrice() {
        // given
        final int expectedPrice = 28;
        final Meal meal = new Meal(35, 1, null);
        // when
        final int discountedPrice = meal.getDiscountedPrice(7);
        // then
        assertEquals(expectedPrice, discountedPrice, "The commentary");

        // hamcrest
        assertThat(discountedPrice, equalTo(expectedPrice));
        assertThat(discountedPrice, is(expectedPrice));

        // assertJ
        Assertions.assertThat(expectedPrice).isEqualTo(discountedPrice);
    }

    @Test
    void test_referencesToTheSameObjectShouldBeEqual() {
        // given
        final Meal mealFirst = new Meal(10, 1, null);
        Meal mealSecond;
        // when
        mealSecond = mealFirst;
        // then
        assertSame(mealFirst, mealSecond, "The commentary");

        // assertJ
        Assertions.assertThat(mealFirst).isSameAs(mealSecond);

        // hamcrest
        assertThat(mealSecond, equalTo(mealFirst));
        assertThat(mealSecond, is(mealFirst));
        assertThat(mealFirst, sameInstance(mealSecond));
    }

    @Test
    void test_referencesToNotTheSameObjectShouldNotBeEqual() {
        // given  // then
        final Meal mealFirst = new Meal(10, 1, null);
        final Meal mealSecond = new Meal(20, 1, null);
        // when
        assertNotSame(mealSecond, mealFirst, "The commentary");

        // assertJ
        Assertions.assertThat(mealFirst).isNotSameAs(mealSecond);

        // hamcrest
        assertThat(mealSecond, not(mealFirst));
        assertThat(mealSecond, not(sameInstance(mealFirst)));
    }

    @Test
    void test_twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {
        // given // when
        final Meal mealFirst = new Meal(10, 1, "Pizza");
        final Meal mealSecond = new Meal(10, 1, "Pizza");
        // then
        assertEquals(mealFirst, mealSecond, "The commentary");

        // assertJ
        Assertions.assertThat(mealFirst).isEqualTo(mealSecond);

        // hamcrest
        assertThat(mealSecond, equalTo(mealFirst));
        assertThat(mealSecond, is(mealFirst));
    }

    @Test
    void test_exceptionShouldBeThrownIfDiscountIsHigherThanThePrice() {
        // given
        final Meal meal = new Meal(8, 1, "Soup");

        // when // then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(40));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 18, 19})
    void test_mealPricesShouldBeLowerThan20(int price) {
        // given // when // then
        assertThat(price, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void test_burgersShouldHaveCorrectNameAndPrice(String name, Integer price) {
        // given // when // then
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    @ParameterizedTest
    @MethodSource("createCakeName")
    void test_cakeNamesShouldEndWithCake(String name) {
        // given // when // then
        assertThat(name, notNullValue());
        assertThat(name, endsWith("cake"));
    }

    @ExtendWith(IllegalArgumentExceptionIgnoreExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 8})
    void test_mealPricesShouldBeLowerThan10(int price) {
        // given // when
        if (price > 5) {
            throw new IllegalArgumentException();
        }

        // then
        assertThat(price, lessThan(20));
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestCollection() {
        return Arrays.asList(
                dynamicTest("Dynamic test 1", () -> assertThat(5, lessThan(6))),
                dynamicTest("Dynamic test 2", () -> assertEquals(4, 2 * 2))
        );
    }

    @TestFactory
    Collection<DynamicTest> calculateMealPrices() {
        final Order order = Order.builder().build();
        order.addMealToOrder(order, new Meal(10, 2, "Hamburger"));
        order.addMealToOrder(order, new Meal(7, 4, "Fries"));
        order.addMealToOrder(order, new Meal(22, 3, "Pizza"));
        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        for (int i = 0; i < order.getMeals().size(); i++) {
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();
            Executable executable = () -> {
                assertThat(calculatePrice(price, quantity), lessThan(67));
            };
            String name = "Test name: " + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }
        return dynamicTests;
    }

    private static Stream<Arguments> createMealsWithNameAndPrice() {
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 12)
        );
    }

    private static Stream<String> createCakeName() {
        final List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitcake", "Cupcake");
        return cakeNames.stream();
    }

    private int calculatePrice(int price, int quantity) {
        return price * quantity;
    }

}
