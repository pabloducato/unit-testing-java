package pl.devfoundry.testing.meal;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.ArrayList;
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

    public List<Meal> find(String name, boolean exactName, int price, SearchType priceSearchType) {

        List<Meal> nameMatches = findByName(name, exactName);

        return findByPriceWithInitialData(price, priceSearchType, nameMatches);

    }

    private List<Meal> findByPriceWithInitialData(int price, SearchType type, List<Meal> initialData) {
        List<Meal> result = new ArrayList<>();

        switch (type) {
            case EXACT:
                result = initialData.stream()
                        .filter(meal -> meal.getPrice() == price)
                        .collect(Collectors.toList());
                break;
            case LESS:
                result = initialData.stream()
                        .filter(meal -> meal.getPrice() < price)
                        .collect(Collectors.toList());
                break;
            case MORE:
                result = initialData.stream()
                        .filter(meal -> meal.getPrice() > price)
                        .collect(Collectors.toList());
                break;

        }

        return result;
    }

    public List<Meal> findByPrice(int price, SearchType type) {

        return findByPriceWithInitialData(price, type, meals);
    }

}
