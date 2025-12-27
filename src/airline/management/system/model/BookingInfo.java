package airline.management.system.model;

public class BookingInfo {
    private final String bookingId;
    private final User passenger;
    private final Flight flight;
    private final Seat seat;

    public BookingInfo(String bookingId, User passenger, Flight flight, Seat seat) {
        this.bookingId = bookingId;
        this.passenger = passenger;
        this.flight = flight;
        this.seat = seat;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public Seat getSeat() {
        return seat;
    }
}
