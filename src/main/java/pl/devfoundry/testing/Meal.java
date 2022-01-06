package pl.devfoundry.testing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

    private int price;
    private String name;

    public int getDiscountedPrice(int discount) {
        return this.price - discount;
    }

}
