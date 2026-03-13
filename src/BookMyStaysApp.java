import java.util.*;
class Reservation{
    private String guestName;
    private String roomType;
    public Reservation(String guestName, String roomType){
        this.guestName = guestName;
        this.roomType = roomType;
    }
    public String getGuestName(){
        return guestName;
    }
    public String getRoomType(){
        return roomType;
    }
}
class RoomInventory{
    private Map<String,Integer> roomAvailability;
    public RoomInventory(){
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single",2);
        roomAvailability.put("Double",2);
        roomAvailability.put("Suite",1);
    }
    public Map<String,Integer> getRoomAvailbility(){
        return roomAvailability;
    }
    public void updateAvailability(String roomType,int count){
        roomAvailability.put(roomType,count);
    }
}
class RoomAllocationService{
    private Set<String> allocatedRoomIds;
    private Map<String,Set<String>> assignedRoomsByType;
    public RoomAllocationService(){
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
        assignedRoomsByType.put("Single",new HashSet<>());
        assignedRoomsByType.put("Double",new HashSet<>());
        assignedRoomsByType.put("Suite",new HashSet<>());
    }
    public void allocateRoom(Reservation reservation, RoomInventory inventory){
        String roomType = reservation.getRoomType();
        Map<String,Integer> availability = inventory.getRoomAvailbility();
        if(availability.get(roomType) > 0){
            String roomId = generateRoomId(roomType);
            allocatedRoomIds.add(roomId);
            assignedRoomsByType.get(roomType).add(roomId);
            inventory.updateAvailability(roomType, availability.get(roomType)-1);
            System.out.println("Booking confirmed for Guest: "
                    + reservation.getGuestName()
                    + ", Room ID: "
                    + roomId);
        }
    }
    private String generateRoomId(String roomType){
        int number = assignedRoomsByType.get(roomType).size() + 1;
        return roomType + "-" + number;
    }
}
class UseCase6RoomAllocation{
    public static void main(String[] args){
        System.out.println("Room Allocation Processing");
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();
        Reservation r1 = new Reservation("Abhi","Single");
        Reservation r2 = new Reservation("Subha","Single");
        Reservation r3 = new Reservation("Vanmathi","Suite");
        service.allocateRoom(r1,inventory);
        service.allocateRoom(r2,inventory);
        service.allocateRoom(r3,inventory);
    }
}