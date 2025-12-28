package airline.management.system.model;

import java.util.Map;
import java.util.UUID;

public final class Flight {
    private final String flightNo;
    private final Aircraft aircraft;
    private final String source;
    private final long startTime;
    private final long endTime;
    private final String dest;
    private final SeatInventory seatInventory;
    private FlightStatus flightStatus;

    public Flight(Aircraft aircraft, String source, long startTime, long endTime, String dest, SeatInventory seatInventory) {
        this.flightNo = UUID.randomUUID().toString();
        this.aircraft = aircraft;
        this.source = source;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dest = dest;
        this.seatInventory = seatInventory;
        this.flightStatus = FlightStatus.ON_TIME;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public String getSource() {
        return source;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getDest() {
        return dest;
    }

    public SeatInventory getSeatInventory() {
        return seatInventory;
    }

    public Map<String, Seat> getSeatMap() {
        return seatInventory.getAvailableSeats();
    }

    public void flightDelayed() {
        this.flightStatus = FlightStatus.DELAYED;
    }
    public void flightCancelled() {
        this.flightStatus = FlightStatus.CANCELLED;
    }
    public Seat getSeat(String seatNo) {
        return seatInventory.getSeat(seatNo);
    }
}
