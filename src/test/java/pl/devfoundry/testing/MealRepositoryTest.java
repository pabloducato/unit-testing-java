package pl.devfoundry.testing;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

class MealRepositoryTest {

    private final MealRepository mealRepository = MealRepository.builder().build();

    @Test
    void shouldBeAbleToAddMealToRepository() {

        // given
        Meal meal = new Meal(10, 1, "Pizza");

        // when
        mealRepository.add(meal);

        // then
        assertThat(mealRepository.getAllMeals().get(0), CoreMatchers.is(meal));
    }

    @Test
    void shouldBeAbleToRemoveMealFromRepository() {

        // given
        Meal meal = new Meal(10, 1, "Pizza");
        mealRepository.add(meal);

        // when
        mealRepository.delete(meal);

        // then
        assertThat(mealRepository.getAllMeals(), CoreMatchers.not(contains(meal)));
    }

}
