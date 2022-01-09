package pl.devfoundry.testing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

    private int price;
    private int quantity;
    private String name;

    public int getDiscountedPrice(int discount) {
        if (discount > this.price) {
            throw new IllegalArgumentException("Discount cannot be higher than the price");
        }
        return this.price - discount;
    }

}
