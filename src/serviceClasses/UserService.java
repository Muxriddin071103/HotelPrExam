package serviceClasses;

import entity.User;

import static serviceInterfaces.Utils.getInt;

public class UserService implements serviceInterfaces.UserService {
    private User currentUser;

    @Override
    public void service(User user) {
        currentUser=user;

    }

    @Override
    public void showHotels() {

    }

    @Override
    public void showReservations() {

    }

    @Override
    public void reserve() {

    }

    @Override
    public void cancelReservation() {

    }

    @Override
    public void rescheduleReservation() {

    }

    @Override
    public void showHistory() {

    }
}
