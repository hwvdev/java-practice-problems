package airline.management.system.model;

import java.util.Objects;

public final class Seat {
    private final String seatNo;
    private final SeatType seatType;
    private final double price;
    private final boolean isReserved;

    enum SeatType {EMERGENCY, WINDOW, AILE, BUSINESS}

    public Seat(String seatNo, SeatType seatType, double price, boolean isReserved) {
        this.seatNo = seatNo;
        this.seatType = seatType;
        this.price = price;
        this.isReserved = isReserved;
    }

    public Seat reserve(boolean reserved) {
        return new Seat(this.seatNo, this.seatType, this.price, true);
    }

    public String getSeatNo() {
        return seatNo;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Double.compare(price, seat.price) == 0 && Objects.equals(seatNo, seat.seatNo) && seatType == seat.seatType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatNo, seatType, price);
    }
}
