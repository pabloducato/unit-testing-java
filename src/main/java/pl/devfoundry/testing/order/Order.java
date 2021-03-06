package pl.devfoundry.testing.order;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import pl.devfoundry.testing.meal.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
public class Order {

    private OrderStatus orderStatus;

    @Singular
    private List<Meal> meals;

    public void addMealToOrder(Order order, Meal meal) {
        final List<Meal> collection = Stream.concat(meals.stream(), Stream.of(meal)).collect(Collectors.toUnmodifiableList());
        order.setMeals(collection);
    }

    public void removeMealFromTheOrder(Order order, Meal meal) {
        final List<Meal> collection = order.getMeals().stream().filter(o -> !o.equals(meal)).collect(Collectors.toUnmodifiableList());
        order.setMeals(collection);
    }

    public void cancel(Order order) {
        order.setMeals(new ArrayList<>());
    }

    public int totalPrice() {
        int sum = this.meals.stream().mapToInt(Meal::getPrice).sum();
        if (sum < 0) {
            throw new IllegalStateException("Price limit exceeded");
        } else {
            return sum;
        }
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

}
