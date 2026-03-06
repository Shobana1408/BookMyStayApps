import java.util.*;
import java.util.function.DoublePredicate;
abstract class Room{
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;
    public Room(int numberOfBeds, int squareFeet, double pricePerNight){
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }
    public void displayRoomDetails(){
        System.out.println("Beds: "+numberOfBeds+"\nSize: "+squareFeet+" sqft\nPrice per night: "+pricePerNight);
    }
}
class SingleRoom extends Room{
    public SingleRoom(){
        System.out.println("Single Room:");
        super(1, 250, 1500.0);
        super.displayRoomDetails();
        System.out.println("Available: 5");
    }
}
class DoubleRoom extends Room{
    public DoubleRoom(){
        System.out.println("Double Room:");
        super(2, 400, 2500.0);
        super.displayRoomDetails();
        System.out.println("Available: 3");
    }
}
class SuiteRoom extends Room{
    public SuiteRoom(){
        System.out.println("Suite Room:");
        super(3, 750, 5000.0);
        super.displayRoomDetails();
        System.out.println("Available: 2");
    }
}
class UseCase2RoomInitialization{
    public static void main(String[] args){
        System.out.println("Hotel Room Initialization\n");
        Room r1 = new SingleRoom();
        System.out.println();
        Room r2 = new DoubleRoom();
        System.out.println();
        Room r3 = new SuiteRoom();



    }
}