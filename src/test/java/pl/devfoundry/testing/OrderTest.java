package pl.devfoundry.testing;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(BeforeAfterExtension.class)
@Tag("test")
class OrderTest {

    private static final Integer VALUE_0 = 0;
    private static final Integer VALUE_1 = 1;
    private static final Integer VALUE_2 = 2;
    private static final Integer VALUE_3 = 3;
    private static final Integer VALUE_4 = 4;
    private static final Integer VALUE_5 = 5;
    private static final Integer VALUE_15 = 15;
    private static final Integer VALUE_18 = 18;

    private static final String BURGER = "Burger";
    private static final String SANDWICH = "Sandwich";
    private static final String DARA_KEBAB = "Dara Kebab";

    private Order order;

    @BeforeEach
    void setUp() {
        log.info("Before each");
        log.info("@BeforeEach -> Launched inside the @BeforeEach annotated `setUp` method");
        order = Order.builder().build();
    }

    @AfterEach
    void tearDown() {
        log.info("After each");
        order.cancel(order);
        log.info("@AfterEach -> Launched inside the @AfterEach annotated `tearDown` method");
    }

    @Test
    void test_assertArrayEquals() {
        // given
        int[] intsFirst = {VALUE_1, VALUE_2, VALUE_3};
        int[] intsSecond = {VALUE_1, VALUE_2, VALUE_3};

        // when // then
        assertArrayEquals(intsFirst, intsSecond);
    }

    @Test
    void test_assertArrayNotEquals() {
        // given
        int[] intsFirst = {VALUE_1, VALUE_2, VALUE_3};
        int[] intsSecond = {VALUE_1, VALUE_2, VALUE_4};

        // when // then
        assertNotSame(intsFirst, intsSecond);
    }

    @Test
    void test_mealListShouldBeEmptyAfterCreationOfAnOrder() {
        // given // when // then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(VALUE_0));
        assertThat(order.getMeals(), hasSize(VALUE_0));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void test_addingMealToOrderShouldIncreaseOrderSize() {
        // given
        final Meal ourFirstMeal = new Meal(VALUE_15, VALUE_1, BURGER);
        final Meal ourSecondMeal = new Meal(VALUE_15, VALUE_1, SANDWICH);

        // when
        order.addMealToOrder(order, ourFirstMeal);

        // then
        assertThat(order.getMeals(), hasSize(VALUE_1));
        assertThat(order.getMeals(), contains(ourFirstMeal));
        assertThat(order.getMeals(), hasItem(ourFirstMeal));
        assertThat(order.getMeals().get(VALUE_0).getPrice(), equalTo(15));
        assertThat(order.getMeals(), not(contains(ourSecondMeal)));
    }

    @Test
    void test_removingMealFromTheOrderShouldDecreaseOrderSize() {
        // given
        final Meal ourFirstMeal = new Meal(VALUE_15, VALUE_1, BURGER);
        final Meal ourSecondMeal = new Meal(VALUE_15, VALUE_1, SANDWICH);

        // when
        order.addMealToOrder(order, ourFirstMeal);
        order.removeMealFromTheOrder(order, ourFirstMeal);

        // then
        assertThat(order.getMeals(), hasSize(VALUE_0));
        assertThat(order.getMeals(), not(contains(ourFirstMeal)));
        assertThat(order.getMeals(), not(contains(ourSecondMeal)));
    }

    // CORRECT -> O -> ORDERING
    @Test
    void test_mealsShouldBeInCorrectOrderAfterAddingThemToOrder() {
        // given
        final Meal ourFirstMeal = new Meal(VALUE_15, VALUE_1, BURGER);
        final Meal ourSecondMeal = new Meal(VALUE_15, VALUE_1, SANDWICH);

        // when
        order.addMealToOrder(order, ourFirstMeal);
        order.addMealToOrder(order, ourSecondMeal);

        // then
        assertThat(order.getMeals(), containsInAnyOrder(ourSecondMeal, ourFirstMeal));
        assertThat(order.getMeals(), contains(ourFirstMeal, ourSecondMeal));
        assertThat(order.getMeals().get(0), is(ourFirstMeal));
        assertThat(order.getMeals().get(1), is(ourSecondMeal));
    }

    @Test
    void test_ifTwoMealListsAreTheSame() {
        // given
        final Meal ourFirstMeal = new Meal(VALUE_15, VALUE_1, BURGER);
        final Meal ourSecondMeal = new Meal(VALUE_15, VALUE_1, SANDWICH);
        final Meal ourThirdMeal = new Meal(VALUE_18, VALUE_1, DARA_KEBAB);
        final List<Meal> listOfMealsOne = Arrays.asList(ourFirstMeal, ourSecondMeal);
        final List<Meal> listOfMealsTwo = Arrays.asList(ourFirstMeal, ourSecondMeal);

        // then
        assertThat(listOfMealsOne, is(listOfMealsTwo));
        assertThat(listOfMealsOne, containsInAnyOrder(ourSecondMeal, ourFirstMeal));
        assertThat(listOfMealsTwo, containsInAnyOrder(ourSecondMeal, ourFirstMeal));
        assertThat(listOfMealsOne, not(contains(ourThirdMeal)));
    }


    @Test
    void test_orderTotalPriceShouldNotExceedsMaxIntValue() {
        // given
        final Meal ourFirstMeal = new Meal(Integer.MAX_VALUE, VALUE_1, BURGER);
        final Meal ourSecondMeal = new Meal(Integer.MAX_VALUE, VALUE_1, SANDWICH);

        // when
        order.addMealToOrder(order, ourFirstMeal);
        order.addMealToOrder(order, ourSecondMeal);

        // then
//        int sum = order.totalPrice();
//        log.info(String.valueOf(sum));
        assertThrows(IllegalStateException.class, () -> order.totalPrice());
    }

    // CORRECT -> R -> REFERENCE
    @Test
    void test_emptyOrderTotalPriceShouldEqualsZero() {
        // given
        // Order is created in @BeforeEach method

        // when // then
        assertThat(order.totalPrice(), is(0));
    }

    @Test
    void test_cancelingOrderShouldRemoveAllItemsFromMealsList() {
        // given
        final Meal ourFirstMeal = new Meal(VALUE_15, VALUE_1, BURGER);
        final Meal ourSecondMeal = new Meal(VALUE_5, VALUE_1, SANDWICH);

        // when
        order.addMealToOrder(order, ourFirstMeal);
        order.addMealToOrder(order, ourSecondMeal);
        order.cancel(order);

        // then
        assertThat(order.getMeals().size(), is(0));
    }
}
