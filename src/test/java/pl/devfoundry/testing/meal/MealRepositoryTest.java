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
    private static final String MEAL_PIZZA_STARTING_CHARS = "Pi";

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
    void shouldBeAbleToFindMealByExactName() {

        // given
        final Meal meal = new Meal(10, 1, MEAL_PIZZA);
        final Meal meal2 = new Meal(10, 1, MEAL_PIZZA_STARTING_CHARS);
        mealRepository.add(meal);
        mealRepository.add(meal2);

        // when
        List<Meal> results = mealRepository.findByName(MEAL_PIZZA, true);

        // then
        assertThat(results.size(), is(1));
    }

    @Test
    void shouldBeAbleToFindMealByStartingLetters() {

        // given
        final Meal meal = new Meal(10, 1, MEAL_PIZZA);
        final Meal meal2 = new Meal(10, 1, MEAL_PIZZA_STARTING_CHARS);
        mealRepository.add(meal);
        mealRepository.add(meal2);

        // when
        List<Meal> results = mealRepository.findByName(MEAL_PIZZA_STARTING_CHARS, false);

        // then
        assertThat(results.size(), is(2));
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

    @Test
    void shouldBeAbleToFindMealByExactPrice() {
        //given
        Meal meal = new Meal(10, 1, "Pizza");
        Meal meal2 = new Meal(9, 1, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByPrice(10, SearchType.EXACT);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldBeAbleToFindMealByLesserPrice() {
        //given
        Meal meal = new Meal(10, 1, "Pizza");
        Meal meal2 = new Meal(9, 1, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByPrice(10, SearchType.LESS);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal2));
    }

    @Test
    void shouldBeAbleToFindMealByHigherPrice() {
        //given
        Meal meal = new Meal(11, 1, "Pizza");
        Meal meal2 = new Meal(9, 1, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.findByPrice(10, SearchType.MORE);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldFindByExactNameAndExactPrice() {
        //given
        Meal meal = new Meal(10, 1, "Pizza");
        Meal meal2 = new Meal(9, 1, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 10, SearchType.EXACT);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldFindByFirstLetterAndExactPrice() {
        //given
        Meal meal = new Meal(10, 1, "Pizza");
        Meal meal2 = new Meal(9, 1, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("B", false, 9, SearchType.EXACT);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal2));
    }

    @Test
    void shouldFindByExactNameAndLowerPrice() {
        //given
        Meal meal = new Meal(10, 1, "Pizza");
        Meal meal2 = new Meal(9, 1, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 11, SearchType.LESS);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldFindByFirstLetterAndLowerPrice() {
        //given
        Meal meal = new Meal(10, 1, "Pizza");
        Meal meal2 = new Meal(9, 1, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("B", false, 10, SearchType.LESS);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal2));
    }

    @Test
    void shouldFindByExactNameAndHigherPrice() {
        //given
        Meal meal = new Meal(10, 1, "Pizza");
        Meal meal2 = new Meal(9, 1, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("Pizza", true, 9, SearchType.MORE);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal));
    }

    @Test
    void shouldFindByFirstLetterAndHigherPrice() {
        //given
        Meal meal = new Meal(10, 1, "Pizza");
        Meal meal2 = new Meal(9, 1, "Burger");

        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> results = mealRepository.find("B", false, 8, SearchType.MORE);

        //then
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(meal2));
    }

}
