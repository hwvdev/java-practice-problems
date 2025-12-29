package airline.management.system.model;

import java.util.UUID;

public final class Flight {
    private final String flightId;
    private final Aircraft aircraft;
    private final String source;
    private final long startTime;
    private final long endTime;
    private final String dest;
    private final FlightStatus flightStatus;

    public Flight(Aircraft aircraft, String source, long startTime, long endTime, String dest) {
        this.flightId = UUID.randomUUID().toString();
        this.aircraft = aircraft;
        this.source = source;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dest = dest;
        this.flightStatus = FlightStatus.ON_TIME;
    }

    public Flight(String flightId, Aircraft aircraft, String source, long startTime, long endTime, String dest, FlightStatus flightStatus) {
        this.flightId = flightId;
        this.aircraft = aircraft;
        this.source = source;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dest = dest;
        this.flightStatus = flightStatus;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public Flight updateSchedule(long startTime, long endTime) {
        return new Flight(this.flightId, this.aircraft, this.source, startTime, endTime, this.dest, this.flightStatus);
    }

    public Flight updateStatus(FlightStatus flightStatus) {
        return new Flight(this.flightId, this.aircraft, this.source, this.startTime, this.endTime, this.dest, flightStatus);
    }

    public String getFlightId() {
        return flightId;
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
}
