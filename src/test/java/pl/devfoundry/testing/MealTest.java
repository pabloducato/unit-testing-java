package pl.devfoundry.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

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
        final Meal meal = new Meal(35, null);
        // when
        final int discountedPrice = meal.getDiscountedPrice(7);
        // then
        assertEquals(expectedPrice, discountedPrice, "The commentary");
        assertThat(discountedPrice, equalTo(expectedPrice));
        assertThat(discountedPrice, is(expectedPrice));
    }

    @Test
    void test_referencesToTheSameObjectShouldBeEqual() {
        // given
        final Meal mealFirst = new Meal(10, null);
        Meal mealSecond;
        // when
        mealSecond = mealFirst;
        // then
        assertSame(mealFirst, mealSecond, "The commentary");
        assertThat(mealSecond, equalTo(mealFirst));
        assertThat(mealSecond, is(mealFirst));
    }

    @Test
    void test_referencesToNotTheSameObjectShouldNotBeEqual() {
        // given  // then
        final Meal mealFirst = new Meal(10, null);
        final Meal mealSecond = new Meal(20, null);
        // when
        assertNotSame(mealSecond, mealFirst, "The commentary");
        assertThat(mealSecond, not(mealFirst));
    }

    @Test
    void test_twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {
        // given // when
        final Meal mealFirst = new Meal(10, "Pizza");
        final Meal mealSecond = new Meal(10, "Pizza");
        // then
        assertEquals(mealFirst, mealSecond, "The commentary");
        assertThat(mealSecond, equalTo(mealFirst));
        assertThat(mealSecond, is(mealFirst));
    }

}
