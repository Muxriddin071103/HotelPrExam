package serviceClasses;

import database.DB;
import entity.Hotel;
import entity.Reservation;
import entity.Role;
import entity.User;

import static serviceInterfaces.Utils.*;

public class AdminService implements serviceInterfaces.AdminService {
    private DB db = DB.getInstance();
    private User currentUser;
    HotelService hotelService = new HotelService();
    ReservationService reservationService = new ReservationService();

    @Override
    public void service(User user) {
        currentUser = user;
        while (true){
            switch (getInt("""
                    0 exit
                    1 add hotel
                    2 show hotel
                    3 show hotels
                    4 edit hotel
                    5 delete hotel
                    6 show users
                    7 show reservation history
                    8 cancel reservation
                    9 reserve for User
                    """)){
                case 0 ->{
                    System.out.println("See you soon " + currentUser.getUsername());
                }
                case 1 ->{addHotel();}
                case 2 ->{showHotel();}
                case 3 ->{showHotels();}
                case 4 ->{editHotel();}
                case 5 ->{deleteHotel();}
                case 6 ->{showUsers();}
                case 7 ->{showReservationHistory();}
                case 8 ->{cancelReservation();}
                case 9 ->{reserveForUser();}
            }
        }
    }

    @Override
    public void addHotel() {
        Hotel hotel = new Hotel();
        hotelService.add(hotel);
    }

    @Override
    public void showHotel() {
        System.out.println("Enter id:");
        String id = strScanner.nextLine();
        hotelService.show(id);

    }

    @Override
    public void showHotels() {
        hotelService.showAll();
    }

    @Override
    public void editHotel() {
        Hotel selectedHotel = new Hotel();
        System.out.println("Enter id:");
        String id = strScanner.nextLine();
        for (Hotel hotel : db.hotels){
            if (hotel.getId().equals(id)){
                selectedHotel = hotel;
                break;
            }
        }
        hotelService.edit(id,selectedHotel);
    }

    @Override
    public void deleteHotel() {

        System.out.println("Enter id:");
        String id = strScanner.nextLine();
        hotelService.delete(id);

    }

    @Override
    public void showUsers() {
        System.out.println("All Users:");
        for (User user : db.users){
            if (user.getRole().equals(Role.USER)){
                System.out.println(user);
            }
        }
    }

    @Override
    public void showReservationHistory() {
        System.out.println("Enter id:");
        String id = strScanner.nextLine();
        reservationService.showReservation(id);
    }

    @Override
    public void cancelReservation() {
        System.out.println("Enter id:");
        String id = strScanner.nextLine();
        reservationService.cancelReservation(id);

    }

    @Override
    public void reserveForUser() {
        System.out.println("Enter user ID:");
        String userId = strScanner.nextLine();

        boolean userFound = false;

        for (User user : db.users) {
            if (user.getId().equals(userId)) {
                userFound = true;
                System.out.println("Reservations for user " + user.getName() + ":");
                boolean hasReservations = false;

                for (Reservation reservation : db.reservations) {
                    if (reservation.getUser().getId().equals(userId)) {
                        System.out.println(reservation);
                        hasReservations = true;
                    }
                }
                if (!hasReservations) {
                    System.out.println("No reservations found for user " + user.getName());
                }
                break;
            }
        }
        if (!userFound) {
            System.out.println("No user found with ID " + userId);
        }
    }
}
