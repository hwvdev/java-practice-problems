package rateLimiter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

public final class FixedWindowRateLimiting implements RateLimiter {

    private final int MAX_ALLOWED_REQUEST;
    private final long TIMEOUT;
    private final ConcurrentMap<String, UserRequest> chm = new ConcurrentHashMap<>();

    public FixedWindowRateLimiting(int maxAllowedRequest, int resetTime) {
        this.MAX_ALLOWED_REQUEST = maxAllowedRequest;
        this.TIMEOUT = resetTime;
    }

    public boolean isRequestAllowed(String userId) {
        return rateLimiterAlgo(userId);
    }

    @Override
    public boolean rateLimiterAlgo(String userId) {
        long now = System.currentTimeMillis();
        UserRequest userRequest =  chm.compute(userId,  (k, v) -> {
            if (v == null) {
                return new UserRequest(MAX_ALLOWED_REQUEST -1, now);
            }
            if (v.getStartTimeStamp() + TIMEOUT >= now && v.getAllowedNoOfRequest() > 0) {
                return new UserRequest(v.getAllowedNoOfRequest()-1, v.getStartTimeStamp());
            }
            if (v.getStartTimeStamp() + TIMEOUT < now) {
                return new UserRequest(MAX_ALLOWED_REQUEST-1, now);
            }
            return v;
        });
        return userRequest.getAllowedNoOfRequest()>=0 && userRequest.getStartTimeStamp() + TIMEOUT >= now;
    }

    // remove unused keys which has timed out
    public void cleapupScheduler() {
        long now = System.currentTimeMillis();
        chm.forEach((key, value) -> chm.computeIfPresent(key, (k, v) -> {
            if (v.getStartTimeStamp() + TIMEOUT < now) {
                return null;
            }

            return v;
        }));
    }

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();

        Calendar calendar = Calendar.getInstance();

        Set<String> s = new HashSet<>();
        s.add(null);
        System.out.println(s);
    }
}
