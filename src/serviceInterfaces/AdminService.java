package serviceInterfaces;

import entity.User;

public interface AdminService {
    void service(User user);
    void addHotel();
    void showHotel();
    void showHotels();
    void editHotel();
    void deleteHotel();
    void showUsers();
    void showReservationHistory();
    void cancelReservation();
    void reserveForUser();

//    private static AdminService adminService ;
//    public static AdminService getInstance(){
//        if (adminService == null)
//            adminService=new AdminService();
//        return adminService;
//    }

}
