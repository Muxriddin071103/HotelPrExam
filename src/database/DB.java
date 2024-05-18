package database;

import entity.Hotel;
import entity.Reservation;
import entity.Role;
import entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DB {
    private static DB db;
    public static Set<User> users = new HashSet<>();
    public static List<Hotel> hotels = new ArrayList<>();
    public static List<Reservation> reservations = new ArrayList<>();

    public static DB getInstance() {
        if (db == null) {
            db = new DB();
            db.users.add(new User("Admin", "admin", "admin", Role.ADMIN));
            db.users.add(new User("Ali", "admin", "admin", Role.USER));
            db.users.add(new User("Vali", "admin", "admin", Role.USER));
            db.users.add(new User("Qodir", "admin", "admin", Role.USER));
            db.users.add(new User("Nodir", "admin", "admin", Role.USER));
        }
        return db;
    }

}
