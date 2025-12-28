package airline.management.system.repo;

import airline.management.system.model.Flight;
import airline.management.system.model.Seat;
import airline.management.system.model.SeatInventory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class FlightRepo {
    private final Map<String, Flight> flightMap = new ConcurrentHashMap<>();

    public void saveFlight(Flight flight) {
        flightMap.putIfAbsent(flight.getFlightNo(), flight);
    }

    public Flight getFlightMap(String flightNo) {
        return flightMap.get(flightNo);
    }

    // seat booked and inventory updated and also flight data updated by reference
    public Flight reserveSeat(String seatNo, String flightNo) {
        Flight flight = flightMap.get(flightNo);
        SeatInventory inventoryRepo = flight.getSeatInventory();
        Seat seatDB = flight.getSeat(seatNo);

        Seat reserveSeat = new Seat(seatDB.getSeatNo(), seatDB.getSeatType(), seatDB.getPrice(), true);

        inventoryRepo.reserveSeatBySeatNo(seatNo, reserveSeat);
        return flight;
    }

    public void unreserveSeat(String flightNo, Seat bookedSeat) {
        Flight flight = flightMap.get(flightNo);
        SeatInventory seatInventory = flight.getSeatInventory();

        Seat seatToCancel = new Seat(bookedSeat.getSeatNo(), bookedSeat.getSeatType(), bookedSeat.getPrice(), false);
        seatInventory.unreserveSeat(bookedSeat.getSeatNo(), seatToCancel);
    }

}
