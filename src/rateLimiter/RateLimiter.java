package rateLimiter;

import java.util.concurrent.ConcurrentMap;

public interface RateLimiter {
    public boolean rateLimiterAlgo(String userId);
}
