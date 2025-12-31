package airline.management.system.model;

import java.util.UUID;

public final class Flight {
    private final String flightId;
    private final String aircraftId;
    private final String source;
    private final long startTime;
    private final long endTime;
    private final String dest;
    private final FlightStatus flightStatus;

    public Flight(String aircraft, String source, long startTime, long endTime, String dest) {
        this.flightId = UUID.randomUUID().toString();
        this.aircraftId = aircraft;
        this.source = source;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dest = dest;
        this.flightStatus = FlightStatus.ON_TIME;
    }

    public Flight(String flightId, String aircraft, String source, long startTime, long endTime, String dest, FlightStatus flightStatus) {
        this.flightId = flightId;
        this.aircraftId = aircraft;
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
        return new Flight(this.flightId, this.aircraftId, this.source, startTime, endTime, this.dest, this.flightStatus);
    }

    public Flight updateStatus(FlightStatus flightStatus) {
        return new Flight(this.flightId, this.aircraftId, this.source, this.startTime, this.endTime, this.dest, flightStatus);
    }

    public String getFlightId() {
        return flightId;
    }

    public String getAircraftId() {
        return aircraftId;
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

    @Override
    public String toString() {
        return "Flight{" +
                "flightId='" + flightId + '\'' +
                ", aircraftId='" + aircraftId + '\'' +
                ", source='" + source + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", dest='" + dest + '\'' +
                ", flightStatus=" + flightStatus +
                '}';
    }
}
