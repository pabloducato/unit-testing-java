package pl.devfoundry.testing;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {

    private boolean active = false;
    private Address defaultDeliveryAddress;

    public void activate() {
        this.active = true;
    }

}
