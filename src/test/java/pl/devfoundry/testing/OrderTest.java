package pl.devfoundry.testing;

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

class OrderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_assertArrayEquals() {
        // given
        int[] intsFirst = {1, 2, 3};
        int[] intsSecond = {1, 2, 3};

        // when // then
        assertArrayEquals(intsFirst, intsSecond);
    }

    @Test
    void test_assertArrayNotEquals() {
        // given
        int[] intsFirst = {1, 2, 3};
        int[] intsSecond = {1, 2, 4};

        // when // then
        assertNotSame(intsFirst, intsSecond);
    }

    @Test
    void test_mealListShouldBeEmptyAfterCreationOfAnOrder() {
        // given
        final Order order = Order.builder().build();

        // when // then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void test_addingMealToOrderShouldIncreaseOrderSize() {
        // given
        final Meal ourFirstMeal = new Meal(15, "Burger");
        final Meal ourSecondMeal = new Meal(15, "Sandwich");
        final Order order = Order.builder().build();
        final List<Meal> meals = new ArrayList<>();

        // when
        order.setMeals(order.addMealToOrder(meals, ourFirstMeal));

        // then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(ourFirstMeal));
        assertThat(order.getMeals(), hasItem(ourFirstMeal));
        assertThat(order.getMeals().get(0).getPrice(), equalTo(15));
        assertThat(order.getMeals(), not(contains(ourSecondMeal)));
    }

    @Test
    void test_removingMealFromTheOrderShouldDecreaseOrderSize() {
        // given
        final Meal ourFirstMeal = new Meal(15, "Burger");
        final Meal ourSecondMeal = new Meal(15, "Sandwich");
        final Order order = Order.builder().build();
        final List<Meal> meals = new ArrayList<>();

        // when
        order.setMeals(order.addMealToOrder(meals, ourFirstMeal));
        order.setMeals(order.removeMealFromTheOrder(meals, ourFirstMeal));

        // then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(ourFirstMeal)));
        assertThat(order.getMeals(), not(contains(ourSecondMeal)));
    }

    @Test
    void test_mealsShouldBeInCorrectOrderAfterAddingThemToOrder() {
        // given
        final Meal ourFirstMeal = new Meal(15, "Burger");
        final Meal ourSecondMeal = new Meal(15, "Sandwich");
        final Order order = Order.builder().build();
        final List<Meal> meals = new ArrayList<>();

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
        final Meal ourFirstMeal = new Meal(15, "Burger");
        final Meal ourSecondMeal = new Meal(15, "Sandwich");
        final Meal ourThirdMeal = new Meal(18, "Dara Kebab");
        final List<Meal> listOfMealsOne = Arrays.asList(ourFirstMeal, ourSecondMeal);
        final List<Meal> listOfMealsTwo = Arrays.asList(ourFirstMeal, ourSecondMeal);

        // then
        assertThat(listOfMealsOne, is(listOfMealsTwo));
        assertThat(listOfMealsOne, containsInAnyOrder(ourSecondMeal, ourFirstMeal));
        assertThat(listOfMealsTwo, containsInAnyOrder(ourSecondMeal, ourFirstMeal));
        assertThat(listOfMealsOne, not(contains(ourThirdMeal)));
    }

}
