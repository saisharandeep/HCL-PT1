import java.util.ArrayList;
import java.util.Scanner;

class Ticket {
    private String passengerName;
    private int seatNumber;
    private boolean isReserved;

    public Ticket(int seatNumber) {
        this.seatNumber = seatNumber;
        this.isReserved = false;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void reserve(String passengerName) {
        this.passengerName = passengerName;
        this.isReserved = true;
    }

    public void cancelReservation() {
        this.passengerName = null;
        this.isReserved = false;
    }

    @Override
    public String toString() {
        if (isReserved) {
            return "Seat " + seatNumber + " is reserved for " + passengerName;
        } else {
            return "Seat " + seatNumber + " is available";
        }
    }
}

public class TicketReservationSystem {
    private static ArrayList<Ticket> tickets = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeTickets();

        while (true) {
            System.out.println("\nTicket Reservation System");
            System.out.println("1. View Available Seats");
            System.out.println("2. Reserve a Seat");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableSeats();
                    break;
                case 2:
                    reserveSeat();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    System.out.println("Thank you for using the Ticket Reservation System!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeTickets() {
        int totalSeats = 10; // Change this to the number of seats you want to offer
        for (int i = 1; i <= totalSeats; i++) {
            tickets.add(new Ticket(i));
        }
    }

    private static void viewAvailableSeats() {
        System.out.println("\nAvailable Seats:");
        for (Ticket ticket : tickets) {
            if (!ticket.isReserved()) {
                System.out.println(ticket);
            }
        }
    }

    private static void reserveSeat() {
        System.out.print("Enter the seat number you want to reserve: ");
        int seatNumber = scanner.nextInt();

        if (seatNumber < 1 || seatNumber > tickets.size()) {
            System.out.println("Invalid seat number.");
            return;
        }

        Ticket selectedTicket = tickets.get(seatNumber - 1);
        if (selectedTicket.isReserved()) {
            System.out.println("Sorry, this seat is already reserved.");
        } else {
            System.out.print("Enter passenger name: ");
            String passengerName = scanner.next();
            selectedTicket.reserve(passengerName);
            System.out.println("Seat " + seatNumber + " has been reserved for " + passengerName);
        }
    }

    private static void cancelReservation() {
        System.out.print("Enter the seat number you want to cancel reservation for: ");
        int seatNumber = scanner.nextInt();

        if (seatNumber < 1 || seatNumber > tickets.size()) {
            System.out.println("Invalid seat number.");
            return;
        }

        Ticket selectedTicket = tickets.get(seatNumber - 1);
        if (selectedTicket.isReserved()) {
            selectedTicket.cancelReservation();
            System.out.println("Reservation for seat " + seatNumber + " has been canceled.");
        } else {
            System.out.println("This seat is not reserved.");
        }
    }
}
