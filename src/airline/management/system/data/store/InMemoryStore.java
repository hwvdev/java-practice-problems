package airline.management.system.data.store;

import airline.management.system.model.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class InMemoryStore {
    public final ConcurrentMap<String, Aircraft> aircraftMap = new ConcurrentHashMap<>(); //  aircraftNo, aircraft
    public final ConcurrentMap<String, User> userByIdMap = new ConcurrentHashMap<>();  // email, User
    public final ConcurrentMap<String, Flight> flightIdFlightMap = new ConcurrentHashMap<>(); // flightNo, Flight
    public final ConcurrentMap<String, List<Seat>> aircraftSeatMap = new ConcurrentHashMap<>(); // aircraftId, List<Seat>
    public final ConcurrentMap<String, ConcurrentMap<String, Seat>> currentSeatMapByFlightId = new ConcurrentHashMap<>();
    public final ConcurrentMap<String, Booking> bookingMapByBookingId = new ConcurrentHashMap<>();

}
