package airline.management.system.model;

public class Seat {
    private final String seatNo;
    private final SeatType seatType;
    private final double price;
    private  boolean isVacant;
    enum SeatType {EMERGENCY, WINDOW, AILE, BUSINESS}

    public Seat(String seatNo, SeatType seatType, double price) {
        this.seatNo = seatNo;
        this.seatType = seatType;
        this.price = price;
        this.isVacant = true;
    }
    public Seat(Seat seat) {
        this.seatNo = seat.seatNo;
        this.seatType = seat.seatType;
        this.price = seat.price;
        this.isVacant = seat.isVacant;
    }

    public void reserve() {
        if (!isVacant)
            throw new RuntimeException("Seat is Reserved");
        isVacant = false;
    }

    public void unReserve() {
        if (isVacant)
            throw new RuntimeException("Seat Already Unreserved");
        isVacant = true;
    }

    public String getSeatNo() {
        return seatNo;
    }
    public double getPrice() {
        return price;
    }
    public boolean isVacant() {
        return isVacant;
    }
}
