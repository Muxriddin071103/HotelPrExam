package serviceClasses;

import database.DB;
import entity.Role;
import entity.User;

import java.util.Objects;

import static serviceInterfaces.Utils.*;

public class AuthService implements serviceInterfaces.AuthService {
    DB db = DB.getInstance();
    AdminService adminService = new AdminService();
    UserService userService = new UserService();

    public void service(){
        while (true) {
            switch (getInt("""
                    0 exit
                    1 sign in
                    2 sign up
                    """)) {
                case 0 -> {
                    System.out.println("see you soon!");
                    return;
                }
                case 1 -> {
                    signIn();
                }
                case 2 -> {
                    signUp();
                }
            }
        }
    }
    @Override
    public void signUp() {
        User user = new User();
        System.out.println("Enter name: ");
        user.setName(strScanner.nextLine());
        System.out.println("Enter username: ");
        user.setUsername(strScanner.nextLine());
        System.out.println("Enter password: ");
        user.setPassword(strScanner.nextLine());
        user.setRole(Role.USER);
        if (db.users.add(user)){
            System.out.println("Successfully signed up!");
        } else {
            System.out.println("Username exists!");
        }
    }

    @Override
    public void signIn() {
        System.out.println("enter username");
        String username = strScanner.nextLine();
        System.out.println("enter password");
        String password = strScanner.nextLine();
        for (User user : db.users) {
            if (Objects.equals(user.getUsername(), username) &&
                    Objects.equals(user.getPassword(), password)) {
                switch (user.getRole().name()) {
                    case "ADMIN" -> {
                        adminService.service(user);
                    }
                    case "USER" -> {
                        userService.service(user);
                    }
                }
            }
        }
    }
}
