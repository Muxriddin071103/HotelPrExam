package serviceClasses;

import entity.User;

import static serviceInterfaces.Utils.*;

public class AdminService implements serviceInterfaces.AdminService {
    private User currentUser;

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
                    5 
                    
                    
                    """)){

            }
        }
    }

    @Override
    public void addHotel() {

    }

    @Override
    public void showHotel() {

    }

    @Override
    public void showHotels() {

    }

    @Override
    public void editHotel() {

    }

    @Override
    public void deleteHotel() {

    }

    @Override
    public void showUsers() {

    }

    @Override
    public void showReservationHistory() {

    }

    @Override
    public void cancelReservation() {

    }

    @Override
    public void reserveForUser() {

    }
}
