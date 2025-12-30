package airline.management.system.service;

import airline.management.system.model.*;
import airline.management.system.repo.BookingRepo;
import airline.management.system.repo.FlightRepo;
import airline.management.system.repo.SeatRepo;

public class BookingService {
    private final BookingRepo bookingRepo;
    private final FlightRepo flightRepo;
    private final SeatRepo seatRepo;

    public BookingService(BookingRepo bookingRepo, FlightRepo flightRepo, SeatRepo seatRepo) {
        this.bookingRepo = bookingRepo;
        this.flightRepo = flightRepo;
        this.seatRepo = seatRepo;
    }

    public Booking reserveBooking(String seatId, String flightId, User passagner) {
        Object lock = LockRegistry.getLockRegistryInstance().getLock(flightId);

        Flight flight = flightRepo.getFlightById(flightId);
        if (flight == null)
            throw new RuntimeException("Flight does not exist");
        if (flight.getFlightStatus() != FlightStatus.BOOKING_OPEN)
            throw new RuntimeException("Booking closed");

        synchronized (lock) {
            seatRepo.reserve(flightId, seatId);
            Booking booking = new Booking(passagner.getUserId(), flightId, seatId);
            bookingRepo.saveBooking(booking);
            return booking;
        }
    }

    public Booking cancelBooking(String bookingId) {
            Booking currentBooking = bookingRepo.getBookingById(bookingId).orElseThrow(() -> new RuntimeException("Invalid booking"));
            Flight flight = flightRepo.getFlightById(currentBooking.getFlightId());
            if (flight.getFlightStatus() != FlightStatus.BOOKING_OPEN) {
                throw new RuntimeException("Cannot cancel booking after closure of booking period");
            }

        Object lock = LockRegistry.getLockRegistryInstance().getLock(bookingId);
        synchronized (lock) {
            seatRepo.unReserve(currentBooking.getFlightId(), currentBooking.getSeatId());
            Booking cancelledBooking = currentBooking.updateStatus(BookingStatus.CANCELLED);
            bookingRepo.cancelBooking(cancelledBooking);
            return cancelledBooking;
        }
    }
}
