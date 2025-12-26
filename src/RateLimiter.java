import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class RateLimiter {
    private final ConcurrentMap<String, UserRequestInfo> userMap;
    private final int N; // N request per T sec
    private final int T;

    public static void main(String[] args) {
        String s1 = "vijay";
        String s2 = new String("vijay");
        String s3 = s2.intern();
        System.out.println(s3==s1);
        System.out.println(s2==s1);
    }

    public RateLimiter(int capacity, int time) {
        this.userMap = new ConcurrentHashMap<>();
        this.N = capacity;
        this.T = time;
    }

    public boolean allowedRequests(String userId) {
        long currentTimeStamp = System.currentTimeMillis();
        userMap.putIfAbsent(userId, new UserRequestInfo(0, currentTimeStamp));
//        UserRequestInfo info = userMap.getOrDefault(userId, new UserRequestInfo(0, currentTimeStamp));
        AtomicBoolean isAllowed = new AtomicBoolean(false);
         userMap.compute(userId, (k, v) -> {
             if (v==null) return new UserRequestInfo(1, currentTimeStamp);
             if (v.startTimestamp + T > currentTimeStamp) {
                 if (v.noOfRequest < N) {
                     v.noOfRequest++;
                     isAllowed.set(true);
                 }
             } else {
                 // resetToken();
                 v.noOfRequest = 1;
                 v.startTimestamp = currentTimeStamp;
                 isAllowed.set(true);
             }
             return v;
         }
        );
        return isAllowed.get();
    }

    static class UserRequestInfo {
        int noOfRequest;
        long startTimestamp;

        public UserRequestInfo(int noOfRequest, long timestamp) {
            this.noOfRequest = noOfRequest;
            this.startTimestamp = timestamp;
        }
    }
}
