package parkingLot.DTO;

public class Ticket {
    long ticketId;
    String licenseNo;
    long entryTime;
    long exitTime;
    int floorNo;
    int slotNo;

    public Ticket(long ticketId, String licenseNo, long entryTime, long exitTime, int floorNo, int slotNo) {
        this.ticketId = ticketId;
        this.licenseNo = licenseNo;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.floorNo = floorNo;
        this.slotNo = slotNo;
    }
}
