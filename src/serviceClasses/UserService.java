package serviceClasses;

import database.DB;
import entity.Reservation;
import entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static serviceInterfaces.Utils.getInt;
import static serviceInterfaces.Utils.strScanner;

public class UserService implements serviceInterfaces.UserService {
    private DB db = DB.getInstance();
    private User currentUser;
    ReservationService reservationService = new ReservationService();
    HotelService hotelService = new HotelService();

    @Override
    public void service(User user) {
        currentUser=user;
        while (true){
            switch (getInt("""
                    0 exit
                    1 show hotels
                    2 show reservations
                    3 reserve
                    4 cancel reservation
                    5 reschedule reservation
                    6 show history
                    """)){
                case 0 -> {
                    System.out.println("See you soon " + currentUser.getName());
                }
                case 1 -> {showHotels();}
                case 2 -> {showReservations();}
                case 3 -> {reserve();}
                case 4 -> {cancelReservation();}
                case 5 -> {rescheduleReservation();}
                case 6 -> {showHistory();}
            }
        }

    }

    @Override
    public void showHotels() {
        hotelService.showAll();
    }

    @Override
    public void showReservations() {
        reservationService.showAllReservations();
    }

    @Override
    public void reserve() {
        reservationService.addReservation(new Reservation());
    }

    @Override
    public void cancelReservation() {
        System.out.println("Enter id: ");
        String id = strScanner.nextLine();
        reservationService.cancelReservation(id);
    }

    @Override
    public void rescheduleReservation() {
        System.out.println("Enter id: ");
        String id = strScanner.nextLine();
        System.out.println("Start date (YYYY-MM-DD): ");
        LocalDate from = LocalDate.parse(strScanner.nextLine());
        System.out.println("End date (YYYY-MM-DD):");
        LocalDate to = LocalDate.parse(strScanner.nextLine());
        reservationService.rescheduleReservation(id,from,to);
    }

    @Override
    public void showHistory() {
        System.out.println("Reservation History:");
        List<Reservation> userReservations = new ArrayList<>();

        for (Reservation reservation : db.reservations) {
            if (reservation.getUser().equals(currentUser)) {
                userReservations.add(reservation);
            }
        }

        if (userReservations.isEmpty()) {
            System.out.println("No reservation history found for user " + currentUser.getName());
        } else {
            for (Reservation reservation : userReservations) {
                System.out.println(reservation);
            }
        }
    }

}
