import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class AddOnService {
    private String serviceName;
    private double cost;
    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }
    public String getServiceName() {
        return serviceName;
    }
    public double getCost() {
        return cost;
    }
}
class AddOnServiceManager {
    private Map<String, List<AddOnService>> servicesByReservation;
    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }
    public void addService(String reservationId, AddOnService service) {
        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());
        servicesByReservation.get(reservationId).add(service);
    }
    public double calculateTotalServiceCost(String reservationId) {
        double total = 0;
        List<AddOnService> services = servicesByReservation.get(reservationId);
        if (services != null) {
            for (AddOnService service : services) {
                total += service.getCost();
            }
        }
        return total;
    }
}
class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();
        String reservationId = "Single-1";
        AddOnService breakfast = new AddOnService("Breakfast", 500.0);
        AddOnService spa = new AddOnService("Spa", 1000.0);
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);
        double totalCost = manager.calculateTotalServiceCost(reservationId);
        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}
