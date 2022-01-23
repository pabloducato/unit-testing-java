package pl.devfoundry.testing.meal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

class MealRepositoryTest {

    private static final String MEAL_PIZZA = "Pizza";

    private MealRepository mealRepository;

    @BeforeEach
    void setUp() {
        mealRepository = MealRepository.builder().build();
    }

    @Test
    void shouldBeAbleToAddMealToRepository() {

        // given
        Meal meal = new Meal(10, 1, MEAL_PIZZA);

        // when
        mealRepository.add(meal);

        // then
        assertThat(mealRepository.getAllMeals().get(0), is(meal));
    }

    @Test
    void shouldBeAbleToRemoveMealFromRepository() {

        // given
        Meal meal = new Meal(10, 1, MEAL_PIZZA);
        mealRepository.add(meal);

        // when
        mealRepository.delete(meal);

        // then
        assertThat(mealRepository.getAllMeals(), Matchers.not(contains(meal)));
    }

    @Test
    void shouldBeAbleToFindMealByName() {

        // given
        final Meal meal = new Meal(10, 1, MEAL_PIZZA);
        mealRepository.add(meal);

        // when
        List<Meal> results = mealRepository.findByName(MEAL_PIZZA);

        // then
        assertThat(results.size(), is(1));
    }

    @Test
    void shouldBeAbleToFindMealByPrice() {

        // given
        final Meal meal = new Meal(10, 1, MEAL_PIZZA);
        mealRepository.add(meal);

        // when
        List<Meal> results = mealRepository.findByPrice(10);

        // then
        assertThat(results.size(), is(1));
    }

}
