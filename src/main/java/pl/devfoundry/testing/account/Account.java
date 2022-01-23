package pl.devfoundry.testing.account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {

    private boolean active = false;
    private Address defaultDeliveryAddress;
    private String email;

    public void activate() {
        this.active = true;
    }

    public Account(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
        if (defaultDeliveryAddress != null) {
            activate();
        } else {
            this.active = false;
        }
    }

    // CORRECT -> C -> CONFORMANCE
    public void setEmail(String email) {
        if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Wrong email format");
        }
    }

}
