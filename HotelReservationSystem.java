package Task3;

import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        try (Scanner input = new Scanner(System.in)) {
            Reservation reservation = new Reservation(hotel);
            hotel.addRoom(101, "Standard", 50);
            hotel.addRoom(102, "Standard", 50);
            hotel.addRoom(201, "Deluxe", 80);
            hotel.addRoom(202, "Deluxe", 80);
            hotel.addRoom(301, "Standard", 50);
            hotel.addRoom(302, "Standard", 50);
            hotel.addRoom(401, "Deluxe", 80);
            hotel.addRoom(402, "Deluxe", 80);

            System.out.println("-----------------Welcome to Our Hotel--------------------");
            System.out.println("Enter your Name please:");
            String userName = input.nextLine();

            while (true) {
                System.out.println("(Enter choice number)\n1-search for available rooms, available rooms\n2-Make reservation\n3-Exit ");
                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                    System.out.println("Enter room category (Standard/Deluxe): ");
                    String category = input.next();
                    List<Room> availableRooms = hotel.getAvaRooms(category, reservation.getReservedRoom());
                    if (availableRooms.isEmpty()) {
                        System.out.println("No available rooms in the selected category.");
                    } else {
                        System.out.println("Available rooms in category " + category + ":");
                        for (Room room : availableRooms) {
                            System.out.println("Room Number: " + room.getRoomNumber());
                        }
                    }
                    break;

                    case 2:
                        System.out.println("Enter room number for reservation: ");
                        int roomNumber = input.nextInt();
                        System.out.println("Enter room category (Standard/Deluxe): ");
                        String cat = input.next();
                        Room room=new Room(roomNumber, cat, 0);
                        if (hotel.getAvaRooms(cat,room)!=null) {
                            // This room is ready for reservation
                            System.out.println("Enter number of days: ");
                            int numOfDays = input.nextInt();
                            input.nextLine();  // Consume the newline character
                            System.out.println("Enter Check-In date: ");
                            String checkInDate = input.nextLine();
                            System.out.println("Enter Check-Out date: ");
                            String checkOutDate = input.nextLine();

                            // Create a reservation with the provided details
                            Room selectedRoom = new Room(roomNumber, cat, hotel.getPrice(roomNumber, cat));
                            reservation.makeReservation(selectedRoom, numOfDays, checkInDate, checkOutDate, userName);                
                            // Display the reservation details
                            //reservation.displayDetails();
                        } else {
                            System.out.println("Invalid room number or room not available for reservation.");
                        }
                        break;

                    case 3:
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter a valid choice.");
                        break;
                }
            }
        }
    }
}
