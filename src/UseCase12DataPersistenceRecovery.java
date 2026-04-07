import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
class RoomInventory {
    private Map<String, Integer> rooms;
    public RoomInventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 5);
        rooms.put("Double", 3);
        rooms.put("Suite", 2);
    }
    public void setRoomCount(String roomType, int count) {
        rooms.put(roomType, count);
    }
    public int getAvailableRooms(String roomType) {
        return rooms.getOrDefault(roomType, 0);
    }
    public Map<String, Integer> getAllRooms() {
        return rooms;
    }
}
class FilePersistenceService {
    public void saveInventory(RoomInventory inventory, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Map.Entry<String, Integer> entry : inventory.getAllRooms().entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
            writer.close();
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory data.");
        }
    }
    public void loadInventory(RoomInventory inventory, String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            boolean validDataFound = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String roomType = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    inventory.setRoomCount(roomType, count);
                    validDataFound = true;
                }
            }
            reader.close();
            if (!validDataFound) {
                System.out.println("No valid inventory data found. Starting fresh.");
            }
        } catch (Exception e) {
            System.out.println("No valid inventory data found. Starting fresh.");
        }
    }
}
class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        System.out.println("System Recovery");
        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();
        String filePath = "inventory_data.txt";
        persistenceService.loadInventory(inventory, filePath);
        System.out.println();
        System.out.println("Current Inventory:");
        System.out.println("Single: " + inventory.getAvailableRooms("Single"));
        System.out.println("Double: " + inventory.getAvailableRooms("Double"));
        System.out.println("Suite: " + inventory.getAvailableRooms("Suite"));
        persistenceService.saveInventory(inventory, filePath);
    }
}
