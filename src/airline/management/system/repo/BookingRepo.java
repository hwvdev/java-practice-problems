package airline.management.system.repo;

import airline.management.system.model.Booking;
import airline.management.system.model.BookingStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookingRepo {
    private final Map<String, Booking> bookingInfoMap = new ConcurrentHashMap<>();

    public BookingRepo() {
    }

    public void saveBooking(Booking booking) {
        bookingInfoMap.putIfAbsent(booking.getBookingId(), booking);
    }

    public Booking getBookingById(String bookingId) {
        return bookingInfoMap.get(bookingId);
    }

    public void cancelBookingById(String bookingId) {
        bookingInfoMap.computeIfPresent(bookingId, (k, v)-> {
            if (BookingStatus.CANCELLED.equals(v.getBookingStatus()))
                throw new RuntimeException("Booking is cancelled already");
            v.updateBookingStatus(BookingStatus.CANCELLED);
            return v;
        });
    }
}
