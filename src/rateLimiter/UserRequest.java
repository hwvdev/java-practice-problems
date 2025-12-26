package rateLimiter;

import java.util.Objects;

public class UserRequest {
    private final int allowedNoOfRequest;
    private final long startTimeStamp;

    public UserRequest(int allowedNoOfRequest, long startTimeStamp) {
        this.allowedNoOfRequest = allowedNoOfRequest;
        this.startTimeStamp = startTimeStamp;
    }

    public int getAllowedNoOfRequest() {
        return allowedNoOfRequest;
    }

    public long getStartTimeStamp() {
        return startTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return allowedNoOfRequest == that.allowedNoOfRequest && startTimeStamp == that.startTimeStamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(allowedNoOfRequest, startTimeStamp);
    }
}
