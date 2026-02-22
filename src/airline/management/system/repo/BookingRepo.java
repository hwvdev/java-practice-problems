package airline.management.system.repo;

import airline.management.system.data.store.InMemoryStore;
import airline.management.system.model.Booking;

import java.util.Map;
import java.util.Optional;

public class BookingRepo {
    private final Map<String, Booking> bookingMapByBookingId;

    public BookingRepo(InMemoryStore store) {
        this.bookingMapByBookingId = store.bookingMapByBookingId;
    }

    public void saveBooking(Booking booking) {
        bookingMapByBookingId.compute(booking.getBookingId(), (bookId, existingBooking) -> {
            if (existingBooking!=null) {
                throw new RuntimeException("Booking already exists");
            }
            return booking;
        });
    }

    public Optional<Booking> getBookingById(String bookingId) {
        return Optional.ofNullable(bookingMapByBookingId.get(bookingId)).map(booking
                -> new Booking(bookingId, booking.getPassenger(), booking.getFlightId(), booking.getSeatId(), booking.getBookingStatus()));
    }

    public void cancelBooking(Booking cancelledBooking) {
        bookingMapByBookingId.compute(cancelledBooking.getBookingId(), (id, existingBooking) -> {
            if (existingBooking == null) {
                throw new RuntimeException("Booking is invalid");
            }
            return cancelledBooking;
        });
    }

    public void reStoreBooking(Booking currBooking) {
        bookingMapByBookingId.put(currBooking.getBookingId(), currBooking);
    }
}
