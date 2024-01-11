package Task3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


class Room{
   private int numOfRooms;
   private String categories;
   private boolean isAvailable;
   private double price;
   private double totalPrice;
   Room(){}
    Room(int numOfRooms, String categories,double price){
        this.numOfRooms = numOfRooms;
        this.categories =categories;
        this.price=price;
        this.isAvailable = true;
    }
    public double getPrice(){
        return price;
    }
    public void totalPrice(int Days,double price)
    {
        this.totalPrice = price*Days;
    }
    
    public double getTotalPrice(){
        return this.totalPrice;
    }
     public int getRoomNumber() {
        return numOfRooms;
    }

    public String getCategory() {
        return categories;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
 public boolean bookRoom(int days) {
    System.out.println("isAvailable "+isAvailable);
    if (isAvailable) {
        totalPrice(days, this.price);
        isAvailable = false;
        return true; // Booking successful
    } else {
        System.out.println("Room " + getRoomNumber() + " is not available for booking.");
        isAvailable = false;
        return false; // Booking unsuccessful
    }
}


    public void releaseRoom() {
        this.isAvailable = true;
    }

    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    Room otherRoom = (Room) obj;
    return this.getRoomNumber() == otherRoom.getRoomNumber();
}

@Override
public int hashCode() {
    return Objects.hash(getRoomNumber());
}
}




 class Hotel {
    //one category -->list of rooms
    private Map<String, List<Room>> roomsByCategory;
    Hotel(){
     this.roomsByCategory = new HashMap<>();
    }
    public void addRoom(int roomNum,String category,double price){
        Room room = new Room(roomNum,category,price);
        roomsByCategory.computeIfAbsent(category, k -> new ArrayList<>()).add(room);
    }
    public List<Room> getAvaRooms(String category, Room reservedRoom) {
    List<Room> availableRooms = roomsByCategory.getOrDefault(category, new ArrayList<>());

    availableRooms = availableRooms.stream()
            .filter(room -> room != reservedRoom && room.isAvailable())
            .collect(Collectors.toList());

    return availableRooms;
}
    public void removeRoom(Room room) {
        roomsByCategory.get(room.getCategory()).remove(room);
    }
    public double getPrice(int roomNumber, String category) {
        boolean roomFound = false;

        for (List<Room> rooms : roomsByCategory.values()) {
            for (Room room : rooms) {
                if (room.getRoomNumber() == roomNumber) {
                    roomFound = true;
                    if (room.getCategory().equalsIgnoreCase(category)) {
                        return room.getPrice();
                    } else {
                        System.out.println("Invalid room category for room number " + roomNumber);
                        return 0;
                    }
                }
            }
        }

        if (!roomFound) {
            System.out.println("Room not found for room number " + roomNumber);
            // Set isAvailable to false here
            for (List<Room> rooms : roomsByCategory.values()) {
                for (Room room : rooms) {
                    if (room.getRoomNumber() == roomNumber) {
                        room.setIsAvailable(false);
                        break;
                    }
                }
            }
        }

        return 0;
    }
}
