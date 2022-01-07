package pl.devfoundry.testing;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class Order {

    @Singular
    private List<Meal> meals;

    public List<Meal> addMealToOrder(List<Meal> list, Meal meal) {
        list.add(meal);
        return Collections.unmodifiableList(list);
    }

    public List<Meal> removeMealFromTheOrder(List<Meal> list, Meal meal) {
        list.remove(meal);
        return Collections.unmodifiableList(list);
    }

}
