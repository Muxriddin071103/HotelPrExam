package serviceClasses;

import database.DB;
import entity.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static serviceInterfaces.Utils.*;

public class ReservationService implements serviceInterfaces.ReservationService {
    private DB db = DB.getInstance();

    @Override
    public boolean addReservation(Reservation reservation) {
        System.out.println("Enter user details:");
        User user = createUserFromConsole();

        System.out.println("Enter hotel details:");
        Hotel hotel = createHotelFromConsole();

        System.out.println("Enter floor number:");
        Integer floor = scanner.nextInt();

        System.out.println("Enter room number:");
        Integer room = scanner.nextInt();

        System.out.println("Enter start date (yyyy-MM-dd):");
        LocalDate startDate = LocalDate.parse(strScanner.nextLine());

        System.out.println("Enter end date (yyyy-MM-dd):");
        LocalDate endDate = LocalDate.parse(strScanner.nextLine());

        reservation = new Reservation(user, hotel, floor, room, startDate, endDate);

        return db.reservations.add(reservation);
    }

    public void showAllReservations(){
        System.out.println("All reservations:");
        for (Reservation reservation : db.reservations){
            System.out.println(reservation);
        }
    }

    @Override
    public Reservation showReservation(String id) {
        System.out.println("Enter reservation Id: ");
        id = strScanner.nextLine();
        for (Reservation reservation : db.reservations) {
            if (reservation.getId().equals(id)) {
                return reservation;
            }
        }
        System.out.println("No reservation found with ID " + id + ".");
        return null;
    }

    @Override
    public List<Reservation> showReservationByUser(String id) {
        System.out.println("Enter User id: ");
        id = strScanner.nextLine();
        for (Reservation reservation : db.reservations){
            if (Objects.equals(reservation.getUser().getId(),id)){
                System.out.println(reservation);
            }
        }
        System.out.println("No reservation found with ID " + id + ".");
        return null;
    }

    @Override
    public List<Reservation> showReservationByHotel(String id) {
        System.out.println("Enter Hotel id: ");
        id = strScanner.nextLine();
        for (Reservation reservation : db.reservations){
            if (Objects.equals(reservation.getHotel().getId(),id)){
                System.out.println(reservation);
            }
        }
        System.out.println("No reservation found with ID " + id + ".");
        return null;
    }

    @Override
    public boolean cancelReservation(String id) {
        System.out.println("Enter reservation ID to cancel: ");
        id = strScanner.nextLine();
        for (Reservation reservation : db.reservations) {
            if (reservation.getId().equals(id) && !reservation.getFinished() && !reservation.getEarlierFinished()) {
                reservation.setFinished(true);
                System.out.println("Reservation with ID " + id + " has been canceled.");
                return true;
            }
        }
        System.out.println("No reservation found with ID " + id + " or it's already finished/earlier finished.");
        return false;
    }

    @Override
    public boolean finishReservation(String id, LocalDate date) {
        System.out.println("Enter id: ");
        id = strScanner.nextLine();
        for (Reservation reservation : db.reservations){
            if (reservation.getId().equals(id) && date.isAfter(reservation.getEndDate())){
                reservation.setFinished(true);
                return true;
            }
        }
        System.out.println("No reservation found with ID " + id + ".");
        return false;
    }

    @Override
    public boolean rescheduleReservation(String id, LocalDate from, LocalDate to) {
        System.out.println("Enter reservation ID to reschedule: ");
        id = strScanner.nextLine();
        for (Reservation reservation : db.reservations) {
            if (reservation.getId().equals(id)) {
                System.out.println("Enter start date (YYYY-MM-DD): ");
                from = LocalDate.parse(strScanner.nextLine());
                System.out.println("Enter end date (YYYY-MM-DD): ");
                to = LocalDate.parse(strScanner.nextLine());
                reservation.setStartDate(from);
                reservation.setEndDate(to);
                System.out.println("Reservation with ID " + id + " has been rescheduled.");
                return true;
            }
        }
        System.out.println("No reservation found with ID " + id + ".");
        return false;
    }

    private User createUserFromConsole() {
        System.out.println("Enter user name:");
        String name = scanner.next();

        System.out.println("Enter username:");
        String username = scanner.next();

        System.out.println("Enter password:");
        String password = scanner.next();

        Role role = Role.USER;

        return new User(name, username, password, role);
    }

    private Hotel createHotelFromConsole() {
        System.out.println("Enter hotel name:");
        String name = scanner.next();

        System.out.println("Choose a location from the following options:");
        for (Location loc : Location.values()) {
            System.out.println(loc.ordinal() + ". " + loc);
        }
        int locationChoice = scanner.nextInt();
        Location location = Location.values()[locationChoice];

        System.out.println("Enter number of floors:");
        Integer floors = scanner.nextInt();

        System.out.println("Enter number of rooms:");
        Integer roomsCount = scanner.nextInt();

        return new Hotel(name, location, floors, roomsCount);
    }

}
