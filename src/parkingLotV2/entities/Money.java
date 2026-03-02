package parkingLotV2.entities;

import java.math.BigDecimal;

public record Money(BigDecimal value, String currency) {

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}
