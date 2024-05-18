package serviceInterfaces;

import entity.User;

public interface UserService {
    void service(User user);
    void showHotels();
    void showReservations();
    void reserve();
    void cancelReservation();
    void rescheduleReservation();
    void showHistory();
}
