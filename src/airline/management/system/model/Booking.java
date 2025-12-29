package airline.management.system.model;

import java.util.UUID;

public final class Booking {
    private final String bookingId;
    private final String passengerId;
    private final String flightId;
    private final String seatId;
    private final BookingStatus bookingStatus;

    public Booking(String passengerId, String flightId, String seatId) {
        this.bookingId = UUID.randomUUID().toString();
        this.passengerId = passengerId;
        this.flightId = flightId;
        this.seatId = seatId;
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public Booking(String bookingId, String passengerId, String flight, String seat, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.flightId = flight;
        this.seatId = seat;
        this.bookingStatus = bookingStatus;
    }

    public Booking updateStatus(BookingStatus bookingStatus) {
        return new Booking(this.bookingId, this.passengerId, this.flightId, this.seatId, bookingStatus);
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

    public String getFlightId() {
        return flightId;
    }

    public String getSeatId() {
        return seatId;
    }

}
