import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
class RoomInventory {
    private Map<String, Integer> rooms;
    public RoomInventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 5);
        rooms.put("Double", 3);
        rooms.put("Suite", 2);
    }
    public void increaseRoom(String roomType) {
        rooms.put(roomType, rooms.getOrDefault(roomType, 0) + 1);
    }
    public int getAvailableRooms(String roomType) {
        return rooms.getOrDefault(roomType, 0);
    }
}
class CancellationService {
    private Stack<String> releasedRoomIds;
    private Map<String, String> reservationRoomTypeMap;
    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }
    public void cancelBooking(String reservationId, RoomInventory inventory) {
        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Cancellation failed. Reservation not found.");
            return;
        }
        String roomType = reservationRoomTypeMap.get(reservationId);
        releasedRoomIds.push(reservationId);
        inventory.increaseRoom(roomType);
        reservationRoomTypeMap.remove(reservationId);
        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }
    public void showRollbackHistory() {
        System.out.println();
        System.out.println("Rollback History (Most Recent First):");
        Stack<String> tempStack = new Stack<>();
        tempStack.addAll(releasedRoomIds);
        while (!tempStack.isEmpty()) {
            System.out.println("Released Reservation ID: " + tempStack.pop());
        }
    }
}
class UseCase10BookingCancellation {
    public static void main(String[] args) {
        System.out.println("Booking Cancellation");
        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();
        cancellationService.registerBooking("Single-1", "Single");
        cancellationService.cancelBooking("Single-1", inventory);
        cancellationService.showRollbackHistory();
        System.out.println();
        System.out.println("Updated Single Room Availability: " + inventory.getAvailableRooms("Single"));
    }
}
