package airline.management.system.service;

import airline.management.system.model.*;
import airline.management.system.repo.BookingRepo;
import airline.management.system.repo.FlightRepo;
import airline.management.system.repo.UserRepo;

public class BookingService {

    private final BookingRepo bookingRepo;
    private final FlightRepo flightRepo;
    private final UserRepo userRepo;

    public BookingService(BookingRepo bookingRepo, FlightRepo flightRepo, UserRepo userRepo) {
        this.flightRepo = flightRepo;
        this.bookingRepo = bookingRepo;
        this.userRepo = userRepo;
    }

    // put transaction
    public synchronized Booking reserveSeat(String seatNo, String flightNo, User passenger) {
        Flight reservedFlight = flightRepo.reserveSeat(seatNo, flightNo);
        Booking booking = new Booking(passenger.getEmail(), reservedFlight, reservedFlight.getSeat(seatNo), BookingStatus.CONFIRMED);
        bookingRepo.saveBooking(booking);
        return booking;
    }

    // put @transaction
    public synchronized void cancelBooking(String bookingId) {
        Booking bookingDetails = bookingRepo.getBookingById(bookingId);
        Seat bookedSeat = bookingDetails.getSeat();

        flightRepo.unreserveSeat(bookingDetails.getFlight().getFlightNo(), bookedSeat);
        bookingRepo.cancelBookingById(bookingId);
    }

}
