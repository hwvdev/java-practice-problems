package airline.management.system.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Flight {
    private final String flightNo;
    private final Aircraft aircraft;
    private final String source;
    private long startTime;
    private long endTime;
    private final String dest;
    private final List<Seat> seatList;
    private FlightStatus flightStatus;

    public enum FlightStatus {DELAYED, ON_TIME, CANCELLED}

    public Flight(Aircraft aircraft, String source, long startTime, long endTime, String dest, List<Seat> seatList) {
        this.flightNo = UUID.randomUUID().toString();
        this.aircraft = aircraft;
        this.source = source;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dest = dest;
        this.seatList = new CopyOnWriteArrayList<>(seatList);
        this.flightStatus = FlightStatus.ON_TIME;
    }

    public void addSeat(Seat seat) {
        seatList.add(new Seat(seat)); // deep copy for immutability
    }

    public Seat reserveSeat() {
        Optional<Seat> seat = seatList.stream().filter(Seat::isVacant).findFirst();
        return seat.orElseThrow(() -> new RuntimeException("All Seats are reserved!"));
    }

    public void unReserveSeat(String seatNo) {
        Optional<Seat> seat = seatList.stream().filter(s -> !s.isVacant() && s.getSeatNo().equals(seatNo)).findFirst();
        seat.ifPresentOrElse(Seat::unReserve, () -> {throw new RuntimeException("Seat is already Un-Reserved");});
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public String getDest() {
        return dest;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public String getSource() {
        return source;
    }

    public String getFlightNo() {
        return flightNo;
    }
}
