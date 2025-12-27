package airline.management.system.model;

public class Aircraft {
    private final String flightName;
    private final String flightNo;

    public Aircraft(String flightName, String flightNo) {
        this.flightName = flightName;
        this.flightNo = flightNo;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getFlightNo() {
        return flightNo;
    }
}
