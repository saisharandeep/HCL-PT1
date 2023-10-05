import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}


class Passenger {
    private String name;
    private int age;
    private String contactNumber;

    public Passenger(String name, int age, String contactNumber) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}

class Train {
    private int trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private int availableSeats;
    private double ticketPrice;
    private String boardingDate;
    private String boardingTime;
    private String arrivalDate;
    private String arrivalTime;
    private String runningStatus;

    public Train(int trainNumber, String trainName, String source, String destination, int availableSeats,
            double ticketPrice, String boardingDate, String boardingTime, String arrivalDate, String arrivalTime) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
        this.boardingDate = boardingDate;
        this.boardingTime = boardingTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.runningStatus = "On Time"; // Default status is "On Time"
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getBoardingDate() {
        return boardingDate;
    }

    public String getBoardingTime() {
        return boardingTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getRunningStatus() {
        return runningStatus;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void updateRunningStatus(String status) {
        this.runningStatus = status;
    }

    public void displayRunningStatus() {
        System.out.println("Running Status of Train " + trainNumber + ": " + runningStatus);
    }

    public double bookTickets(int numberOfTickets) {
        if (numberOfTickets <= availableSeats) {
            availableSeats -= numberOfTickets;
            return numberOfTickets * ticketPrice;
        } else {
            return 0.0; // Not enough available seats
        }
    }
}

public class TrainTicketReservationSystem{
    private static List<Train> trains = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static int reservationCounter = 1;
    private static User currentUser = null; 
    private static List<User> users = new ArrayList<>();

    public static class Reservation {
        private int reservationNumber;
        private Train train;
        private List<Passenger> passengers;
        private String reservationDate;

        public Reservation(int reservationNumber, Train train) {
            this.reservationNumber = reservationNumber;
            this.train = train;
            this.passengers = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.reservationDate = sdf.format(new Date());
        }

        public void addPassenger(Passenger passenger) {
            passengers.add(passenger);
        }

        public List<Passenger> getPassengers() {
            return passengers;
        }

        public int getReservationNumber() {
            return reservationNumber;
        }

        public Train getTrain() {
            return train;
        }

        public String getReservationDate() {
            return reservationDate;
        }

        public double getTotalPrice() {
            return passengers.size() * train.getTicketPrice();
        }
    }

    public static void main(String[] args) {
        initializeTrains();
        initializeUsers(); // Initialize valid users

        Scanner scanner = new Scanner(System.in);

        // Login Page
        while (currentUser == null) {
            System.out.print("Enter username: ");
            String username = scanner.next();
            System.out.print("Enter password: ");
            String password = scanner.next();

            // Check if the entered username and password match any valid user
            currentUser = authenticateUser(username, password);

            if (currentUser == null) {
                System.out.println("Invalid username or password. Please try again.");
            }
        }


        while (true) {
            System.out.println("\nTrain Ticket Reservation System");
            System.out.println("1. View available trains");
            System.out.println("2. Reserve a ticket");
            System.out.println("3. View reservations");
            System.out.println("4. View running status of a train");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableTrains();
                    break;
                case 2:
                    reserveTicket(scanner);
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    viewRunningStatus(scanner);
                    break;
                case 5:
                    currentUser = null;
                    System.out.println("Thank you for using the reservation system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeTrains() {
        trains.add(new Train(101, "Express 101", "City A", "City B", 50, 50.0, "2023-09-25", "10:00 AM", "2023-09-25",
                "2:00 PM"));
        trains.add(new Train(202, "Superfast 202", "City B", "City C", 100, 75.0, "2023-09-25", "12:00 PM",
                "2023-09-25", "4:00 PM"));
        trains.add(new Train(303, "Local 303", "City C", "City D", 200, 30.0, "2023-09-25", "9:00 AM", "2023-09-25",
                "1:00 PM"));
    }

    private static void viewAvailableTrains() {
        System.out.println("\nAvailable Trains:");
        System.out.println(
                "Train Number\tTrain Name\tSource\tDestination\tBoarding Date\tBoarding Time\tArrival Date\tArrival Time\tAvailable Seats\tTicket Price");

        for (Train train : trains) {
            System.out.println(train.getTrainNumber() + "\t\t" + train.getTrainName() + "\t\t"
                    + train.getSource() + "\t" + train.getDestination() + "\t"
                    + train.getBoardingDate() + "\t" + train.getBoardingTime() + "\t"
                    + train.getArrivalDate() + "\t" + train.getArrivalTime() + "\t"
                    + train.getAvailableSeats() + "\t\t" + train.getTicketPrice());
        }
    }

    private static void reserveTicket(Scanner scanner) {
        viewAvailableTrains();

        System.out.print("Enter the Train Number: ");
        int trainNumber = scanner.nextInt();

        Train selectedTrain = null;
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                selectedTrain = train;
                break;
            }
        }

        if (selectedTrain == null) {
            System.out.println("Invalid Train Number.");
            return;
        }

        System.out.print("Enter the number of tickets to reserve: ");
        int numberOfTickets = scanner.nextInt();

        if (numberOfTickets > selectedTrain.getAvailableSeats()) {
            System.out.println("Not enough available seats.");
        } else {
            Reservation reservation = new Reservation(reservationCounter++, selectedTrain);
            for (int i = 0; i < numberOfTickets; i++) {
                System.out.println("\nEnter details for Passenger " + (i + 1));
                System.out.print("Name: ");
                String name = scanner.next();
                System.out.print("Age: ");
                int age = scanner.nextInt();
                System.out.print("Contact Number: ");
                String contactNumber = scanner.next();
                Passenger passenger = new Passenger(name, age, contactNumber);
                reservation.addPassenger(passenger);
            }
            selectedTrain.setAvailableSeats(selectedTrain.getAvailableSeats() - numberOfTickets);

            System.out.println("Reservation successful.");
            System.out.println("Reservation Number: " + reservation.getReservationNumber());
            System.out.println("Total Price: $" + reservation.getTotalPrice());
            System.out.println("Boarding Date and Time: " + selectedTrain.getBoardingDate() + " "
                    + selectedTrain.getBoardingTime());
            System.out.println(
                    "Arrival Date and Time: " + selectedTrain.getArrivalDate() + " " + selectedTrain.getArrivalTime());

            // Simulate payment (for demonstration purposes)
            System.out.println("Total Price: $" + reservation.getTotalPrice());
            boolean paymentSuccess = makePayment(reservation.getTotalPrice());
            if (paymentSuccess) {
                System.out.println("Payment successful.");
                reservations.add(reservation);
            } else {
                System.out.println("Payment failed. Reservation canceled.");
                selectedTrain.setAvailableSeats(selectedTrain.getAvailableSeats() + numberOfTickets);
            }
        }
    }

    private static void viewReservations() {
        System.out.println("\nYour Reservations:");
        System.out.println(
                "Reservation Number\tTrain Name\tSource\tDestination\tBoarding Date\tBoarding Time\tArrival Date\tArrival Time\tNumber of Tickets\tTotal Price\tReservation Date");

        for (Reservation reservation : reservations) {
            Train train = reservation.getTrain();
            System.out.println(reservation.getReservationNumber() + "\t\t" +
                    train.getTrainName() + "\t\t" +
                    train.getSource() + "\t" +
                    train.getDestination() + "\t" +
                    train.getBoardingDate() + "\t" +
                    train.getBoardingTime() + "\t" +
                    train.getArrivalDate() + "\t" +
                    train.getArrivalTime() + "\t\t" +
                    reservation.getPassengers().size() + "\t\t" +
                    reservation.getTotalPrice() + "\t\t" +
                    reservation.getReservationDate());
        }
    }

    private static void viewRunningStatus(Scanner scanner) {
        System.out.print("Enter the Train Number to check its running status: ");
        int trainNumber = scanner.nextInt();

        Train selectedTrain = null;
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                selectedTrain = train;
                break;
            }
        }

        if (selectedTrain == null) {
            System.out.println("Invalid Train Number.");
            return;
        }

        selectedTrain.displayRunningStatus();
    }

    private static boolean makePayment(double amount) {
        // Simulate a payment process (for demonstration purposes)
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter '1' to simulate a successful payment, '0' to simulate a failed payment: ");
        int paymentChoice = scanner.nextInt();
        return paymentChoice == 1;
    }
    
        private static void initializeUsers() {
        // Initialize valid users (you can add more users here)
        users.add(new User("user1", "password1"));
        users.add(new User("user2", "password2"));
    }

    private static User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // User authentication successful
            }
        }
        return null; // User not found or incorrect password
    }
}
