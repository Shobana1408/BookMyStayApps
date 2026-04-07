import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
class Reservation {
    private String guestName;
    private String roomType;
    private String roomId;
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
    public String getGuestName() {
        return guestName;
    }
    public String getRoomType() {
        return roomType;
    }
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
class BookingRequestQueue {
    private Queue<Reservation> queue;
    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }
    public Reservation getNextRequest() {
        return queue.poll();
    }
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
class RoomInventory {
    private Map<String, Integer> availableRooms;
    private Map<String, Integer> roomCounters;
    public RoomInventory() {
        availableRooms = new HashMap<>();
        roomCounters = new HashMap<>();
        availableRooms.put("Single", 5);
        availableRooms.put("Double", 3);
        availableRooms.put("Suite", 2);
        roomCounters.put("Single", 1);
        roomCounters.put("Double", 1);
        roomCounters.put("Suite", 1);
    }
    public boolean isAvailable(String roomType) {
        return availableRooms.getOrDefault(roomType, 0) > 0;
    }
    public String allocateRoomId(String roomType) {
        int count = roomCounters.get(roomType);
        String roomId = roomType + "-" + count;
        roomCounters.put(roomType, count + 1);
        availableRooms.put(roomType, availableRooms.get(roomType) - 1);
        return roomId;
    }
    public int getAvailableRooms(String roomType) {
        return availableRooms.getOrDefault(roomType, 0);
    }
}
class RoomAllocationService {
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        if (inventory.isAvailable(reservation.getRoomType())) {
            String roomId = inventory.allocateRoomId(reservation.getRoomType());
            reservation.setRoomId(roomId);
            System.out.println("Booking confirmed for Guest: " + reservation.getGuestName() + ", Room ID: " + reservation.getRoomId());
        }
    }
}
class ConcurrentBookingProcessor implements Runnable {
    private BookingRequestQueue bookingQueue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;
    public ConcurrentBookingProcessor(BookingRequestQueue bookingQueue, RoomInventory inventory, RoomAllocationService allocationService) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }
    public void run() {
        while (true) {
            Reservation reservation;
            synchronized (bookingQueue) {
                if (bookingQueue.isEmpty()) {
                    break;
                }
                reservation = bookingQueue.getNextRequest();
            }
            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}
class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {
        System.out.println("Concurrent Booking Simulation");
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Double"));
        bookingQueue.addRequest(new Reservation("Kural", "Suite"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));
        Thread t1 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));
        Thread t2 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }
        System.out.println();
        System.out.println("Remaining Inventory:");
        System.out.println("Single: " + inventory.getAvailableRooms("Single"));
        System.out.println("Double: " + inventory.getAvailableRooms("Double"));
        System.out.println("Suite: " + inventory.getAvailableRooms("Suite"));
    }
}
