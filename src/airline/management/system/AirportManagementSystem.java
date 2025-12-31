package airline.management.system;

import airline.management.system.data.store.InMemoryStore;
import airline.management.system.model.*;
import airline.management.system.repo.*;
import airline.management.system.service.*;

import java.util.List;
import java.util.Map;

public class AirportManagementSystem {
    public static void main(String[] args) {
        InMemoryStore store = new InMemoryStore();

        AircraftRepo aircraftRepo = new AircraftRepo(store);
        FlightRepo flightRepo = new FlightRepo(store);
        BookingRepo bookingRepo = new BookingRepo(store);
        SeatLayoutRepo seatLayoutRepo = new SeatLayoutRepo(store);
        SeatRepo seatRepo = new SeatRepo(store);
        UserRepo userRepo = new UserRepo(store);

        UserService userService = new UserService(userRepo);
        AircraftService aircraftService = new AircraftService(aircraftRepo);
        BookingService bookingService = new BookingService(bookingRepo, flightRepo, seatRepo);
        SeatLayoutService seatLayoutService = new SeatLayoutService(seatLayoutRepo);

        User user1 = new User("vijaydev2016@gmail.com", "vijay", UserType.PASSENGER);
        userService.registerUser(user1);
        userService.registerUser(new User("vijaydev@gmail.com", "vijay-1", UserType.PASSENGER));
        userService.registerUser(new User("randomEmail@gmail.com", "random", UserType.PASSENGER));

        aircraftService.registerAircraft(new Aircraft("AIR INDIA", "43214"));
        aircraftService.registerAircraft(new Aircraft("JET AIRWAYS", "63223"));
        aircraftService.registerAircraft(new Aircraft("TEST INDIGO", "98214"));

        Seat seat1 = new Seat("1A", SeatType.BUSINESS, 99000.99, false);
        Seat seat2 = new Seat("2B", SeatType.BUSINESS, 99000.99, false);
        Seat seat3 = new Seat("3C", SeatType.WINDOW, 8888.54, false);
        Seat seat4 = new Seat("5F", SeatType.AILE, 5555.54, false);
        Seat seat5 = new Seat("4D", SeatType.EMERGENCY, 0000.0, false);

        List<Seat> layout1 = List.of(seat1, seat2, seat3, seat4);
        seatLayoutService.saveLayout("63223", layout1);
        List<Seat> layout2 = List.of(seat1, seat2, seat3, seat4, seat5);
        seatLayoutService.saveLayout("98214", layout2);
        seatLayoutService.saveLayout("43214", layout1);

        FlightService flightService = new FlightService(flightRepo, seatRepo, seatLayoutRepo);
        Flight flight1 = new Flight("63223", "Delhi", 46466, 797978, "Mumbai");
        flightService.saveFlight(flight1);
        Flight flight2 = new Flight("98214", "NY", 879879, 3424324, "Pune");
        flightService.saveFlight(flight2);

        Map<String, Seat> seatMap1 = flightService.getSeatLayoutMap(flight1.getFlightId());
        System.out.println(seatMap1);

        Booking booking1 = bookingService.reserveBooking("3C", flight1.getFlightId(), user1);
        seatMap1 = flightService.getSeatLayoutMap(flight1.getFlightId());
        System.out.println(seatMap1);

        Booking booking2 = bookingService.reserveBooking("1A", flight1.getFlightId(), user1);
        seatMap1 = flightService.getSeatLayoutMap(flight1.getFlightId());
        System.out.println(seatMap1);
        bookingService.cancelBooking(booking1.getBookingId());
        seatMap1 = flightService.getSeatLayoutMap(flight1.getFlightId());
        System.out.println(seatMap1);

        Booking booking3 = bookingService.reserveBooking("3C", flight1.getFlightId(), user1);
        seatMap1 = flightService.getSeatLayoutMap(flight1.getFlightId());
        System.out.println(seatMap1);

        System.out.println(booking1.getBookingId());
        System.out.println(booking3.getBookingId());
    }
}
