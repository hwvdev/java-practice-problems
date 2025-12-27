package airline.management.system.service;

import airline.management.system.model.BookingInfo;
import airline.management.system.model.Flight;
import airline.management.system.model.Seat;
import airline.management.system.model.User;
import airline.management.system.repo.BookingRepo;
import airline.management.system.repo.FlightRepo;

import java.util.UUID;

public class BookingService {
    private final UserService userService = new UserService();
    private final FlightRepo flightRepo = FlightRepo.getFlightRepoInstance();
    private final BookingRepo bookingRepo = BookingRepo.getBookingRepoInstance();

    public BookingService() {}

    public BookingInfo bookSeat(String flightNo, String userEmail) {
        User passenger = userService.getUser(userEmail);
        Seat reservedSeat = flightRepo.reserve(flightNo);
        Flight revservedFlight = flightRepo.getFlightInfo(flightNo);

        String bookingId = UUID.randomUUID().toString();
        BookingInfo bookingInfo = new BookingInfo(bookingId, passenger, revservedFlight, reservedSeat);
        return bookingRepo.bookSeat(bookingId, bookingInfo);
    }

    public synchronized void cancelBooking(String bookingId) {
        BookingInfo bookingInfo = bookingRepo.getBookingDetails(bookingId);
        bookingRepo.cancelBooking(bookingId);
        flightRepo.unReserveSeat(bookingInfo.getFlight().getFlightNo(), bookingInfo.getSeat().getSeatNo());
    }

}
