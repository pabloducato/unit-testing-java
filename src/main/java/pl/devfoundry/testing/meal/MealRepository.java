package pl.devfoundry.testing.meal;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
public class MealRepository {

    @Singular
    private List<Meal> meals;

    public void add(Meal meal) {
        meals = Stream.concat(meals.stream(), Stream.of(meal))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Meal> getAllMeals() {
        return meals;
    }

    public void delete(Meal meal) {
        meals = meals.stream()
                .filter(o -> !o.equals(meal))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Meal> findByName(String pizza, boolean exactMatch) {

        List<Meal> result;

        if (exactMatch) {
            result = meals.stream()
                    .filter(meal -> meal.getName().equals(pizza))
                    .collect(Collectors.toUnmodifiableList());
        } else {
            result = meals.stream()
                    .filter(meal -> meal.getName().startsWith(pizza))
                    .collect(Collectors.toUnmodifiableList());
        }

        return result;
    }

    public List<Meal> findByPrice(int mealPrice) {
        return meals.stream()
                .filter(m -> m.getPrice() == mealPrice)
                .collect(Collectors.toUnmodifiableList());
    }
}
