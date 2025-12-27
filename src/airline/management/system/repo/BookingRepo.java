package airline.management.system.repo;

import airline.management.system.model.BookingInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookingRepo {
    private final Map<String, BookingInfo> bookingInfoMap = new ConcurrentHashMap<>();

    private BookingRepo() {
    }

    private static final BookingRepo BOOKING_REPO_INSTANCE;

    static {
        BOOKING_REPO_INSTANCE = new BookingRepo();
    }

    public static BookingRepo getBookingRepoInstance() {
        return BOOKING_REPO_INSTANCE;
    }

    public BookingInfo bookSeat(String bookingId, BookingInfo bookingInfo) {
        return bookingInfoMap.put(bookingId, bookingInfo);
    }

    public BookingInfo getBookingDetails(String bookingId) {
        return bookingInfoMap.getOrDefault(bookingId, null);
    }

    public void cancelBooking(String bookingId) {
        bookingInfoMap.computeIfPresent(bookingId, (k, v) -> {
            v.getSeat().unReserve();
            return v;
        });
    }

}
