import java.util.Map;
import java.util.*;
class RoomInventory {
    private Map<String, Integer> roomAvailability;
    public RoomInventory(){
        roomAvailability = new HashMap<>();
        initializeInventory();;
    }
    private void initializeInventory(){
        roomAvailability.put("Single",5);
        roomAvailability.put("Double",3);
        roomAvailability.put("Suite",2);
    }
    public Map<String, Integer> getRoomAvailbility(){
        return roomAvailability;
    }
    public void updateAvailability(String roomType, int count){
        roomAvailability.put(roomType, count);
    }
}
class InventorySetup{
    public static void main(String[] args){
        RoomInventory ri = new RoomInventory();
        ri.updateAvailability("Single",5);
        ri.updateAvailability("Double",3);
        ri.updateAvailability("Suite",2);
        Map<String, Integer> rooms = ri.getRoomAvailbility();
        System.out.println("Hotel Room Inventory Status\n");
        System.out.println("Single Room:");
        System.out.println("Beds: 1");
        System.out.println("Size: 250 sqft");
        System.out.println("Price per night: 1500.0");
        System.out.println("Available Rooms: " + rooms.get("Single"));
        System.out.println();
        System.out.println("Double Room:");
        System.out.println("Beds: 2");
        System.out.println("Size: 400 sqft");
        System.out.println("Price per night: 2500.0");
        System.out.println("Available Rooms: " + rooms.get("Double"));
        System.out.println();
        System.out.println("Suite Room:");
        System.out.println("Beds: 3");
        System.out.println("Size: 750 sqft");
        System.out.println("Price per night: 5000.0");
        System.out.println("Available Rooms: " + rooms.get("Suite"));
    }
}