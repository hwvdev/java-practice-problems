package airline.management.system.model;

import java.util.UUID;

public final class Booking {
    private final String bookingId;
    private final String passengerId;
    private final Flight flight;
    private final Seat seat;
    private BookingStatus bookingStatus;

    public Booking(String passenger, Flight flight, Seat seat, BookingStatus bookingStatus) {
        this.bookingId = UUID.randomUUID().toString();
        this.passengerId = passenger;
        this.flight = flight;
        this.seat = seat;
        this.bookingStatus = bookingStatus;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getPassenger() {
        return passengerId;
    }

    public Flight getFlight() {
        return flight;
    }

    public Seat getSeat() {
        return seat;
    }

    public void updateBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
