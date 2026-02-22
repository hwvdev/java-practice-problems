package airline.management.system.model;

import java.util.Objects;

public final class Aircraft {
    private final String aircraftName;
    private final String aircraftId;

    public Aircraft(String aircraftName, String aircraftId) {
        this.aircraftName = aircraftName;
        this.aircraftId = aircraftId;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public String getAircraftId() {
        return aircraftId;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aircraft aircraft = (Aircraft) o;
        return Objects.equals(aircraftName, aircraft.aircraftName) && Objects.equals(aircraftId, aircraft.aircraftId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aircraftName, aircraftId);
    }
}
