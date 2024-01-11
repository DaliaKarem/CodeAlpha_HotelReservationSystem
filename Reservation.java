package Task3;
public class Reservation {
    private Room room;
    private Hotel hotel;
    private String person;
    private String checkInDate;
    private String checkOutDate;

    public Reservation(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getReservedRoom() {
        return room;
    }

    public void makeReservation(Room room, int numOfDays, String checkInDate, String checkOutDate, String person) {
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.person = person;

        // Check if the room is available for reservation
        if (room.getPrice() == 0) {
            System.out.println("Invalid room or room not available for reservation.");
            return;
        }

        room.bookRoom(numOfDays);
        hotel.removeRoom(room);
        displayDetails();
    }

    public double calculateTotalPrice(int roomNumber, String cat, int numOfDays) {
        double roomPrice = hotel.getPrice(roomNumber, cat);
        return roomPrice * numOfDays;
    }

    public void displayDetails() {
        System.out.println("------------Reservation Done------------");
        System.out.println("Reservation Details:");
        System.out.println("Guest Name: " + person);
        System.out.println("Room Number: " + room.getRoomNumber());
        System.out.println("Category: " + room.getCategory());
        System.out.println("Check-In Date: " + checkInDate);
        System.out.println("Check-Out Date: " + checkOutDate);
        System.out.println("Total Price: " + room.getTotalPrice());
        System.out.println("----------------------------------------------");
    }
}
