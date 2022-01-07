package pl.devfoundry.testing;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@Slf4j
class OrderTest {

    private static final Integer VALUE_0 = 0;
    private static final Integer VALUE_1 = 1;
    private static final Integer VALUE_2 = 2;
    private static final Integer VALUE_3 = 3;
    private static final Integer VALUE_4 = 4;
    private static final Integer VALUE_15 = 15;
    private static final Integer VALUE_18 = 18;

    private static final String BURGER = "Burger";
    private static final String SANDWICH = "Sandwich";
    private static final String DARA_KEBAB = "Dara Kebab";

    private Order order;
    private final List<Meal> meals = new ArrayList<>();

    @BeforeEach
    void setUp() {
        log.info("@BeforeEach -> Launched inside the @BeforeEach annotated `setUp` method");
        order = Order.builder().build();
    }

    @AfterEach
    void tearDown() {
        order.cancel(meals);
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
        final Meal ourFirstMeal = new Meal(VALUE_15, BURGER);
        final Meal ourSecondMeal = new Meal(VALUE_15, SANDWICH);

        // when
        order.setMeals(order.addMealToOrder(meals, ourFirstMeal));

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
        final Meal ourFirstMeal = new Meal(VALUE_15, BURGER);
        final Meal ourSecondMeal = new Meal(VALUE_15, SANDWICH);

        // when
        order.setMeals(order.addMealToOrder(meals, ourFirstMeal));
        order.setMeals(order.removeMealFromTheOrder(meals, ourFirstMeal));

        // then
        assertThat(order.getMeals(), hasSize(VALUE_0));
        assertThat(order.getMeals(), not(contains(ourFirstMeal)));
        assertThat(order.getMeals(), not(contains(ourSecondMeal)));
    }

    @Test
    void test_mealsShouldBeInCorrectOrderAfterAddingThemToOrder() {
        // given
        final Meal ourFirstMeal = new Meal(VALUE_15, BURGER);
        final Meal ourSecondMeal = new Meal(VALUE_15, SANDWICH);

        // when
        order.setMeals(order.addMealToOrder(meals, ourFirstMeal));
        order.setMeals(order.addMealToOrder(meals, ourSecondMeal));

        // then
        assertThat(order.getMeals(), containsInAnyOrder(ourSecondMeal, ourFirstMeal));
        assertThat(order.getMeals(), contains(ourFirstMeal, ourSecondMeal));
    }

    @Test
    void test_ifTwoMealListsAreTheSame() {
        // given
        final Meal ourFirstMeal = new Meal(VALUE_15, BURGER);
        final Meal ourSecondMeal = new Meal(VALUE_15, SANDWICH);
        final Meal ourThirdMeal = new Meal(VALUE_18, DARA_KEBAB);
        final List<Meal> listOfMealsOne = Arrays.asList(ourFirstMeal, ourSecondMeal);
        final List<Meal> listOfMealsTwo = Arrays.asList(ourFirstMeal, ourSecondMeal);

        // then
        assertThat(listOfMealsOne, is(listOfMealsTwo));
        assertThat(listOfMealsOne, containsInAnyOrder(ourSecondMeal, ourFirstMeal));
        assertThat(listOfMealsTwo, containsInAnyOrder(ourSecondMeal, ourFirstMeal));
        assertThat(listOfMealsOne, not(contains(ourThirdMeal)));
    }

}
