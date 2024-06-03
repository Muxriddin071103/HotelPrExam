package serviceClasses;

import database.DB;
import entity.Hotel;
import entity.Location;

import java.util.List;
import java.util.logging.Logger;

import static serviceInterfaces.Utils.*;

public class HotelService implements serviceInterfaces.HotelService {
    private DB database= DB.getInstance();
    @Override
    public void add(Hotel hotel) {
        System.out.println("Enter the name of the Hotel : ");
        String hotelName= strScanner.nextLine();
        hotel.setName(hotelName);


        System.out.println("Choose a location from the following options:");
        for (Location loc : Location.values()) {
            System.out.println(loc.ordinal() + ". " + loc);
        }
        int locationChoice = scanner.nextInt();
        Location location = Location.values()[locationChoice];
        hotel.setLocation(location);

        System.out.println("Enter the number of floors: ");
        int floorNum=scanner.nextInt();
        hotel.setFloors(floorNum);

        System.out.println("Enter the number of rooms which each floor has: ");
        int roomCount= scanner.nextInt();
        hotel.setRoomsCount(roomCount);

        database.hotels.add(hotel);
    }

    @Override
    public Hotel show(String id) {
        System.out.println("Enter the ID of the hotel wanted to show");
        id= strScanner.nextLine();
        for (Hotel hotel : database.hotels) {
            if (hotel.getId().equals(id)) {
                return hotel;
            }
        }
        return null;
    }

    @Override
    public List<Hotel> showAll() {
       return database.hotels;
    }

    @Override
    public boolean edit(String id, Hotel hotel) {
        Hotel editingHotel = show(id);

        if(editingHotel == null){
            return false;
        } else {
            Logger logger= Logger.getLogger(Hotel.class.getName());

            System.out.println("Enter the new name of the Hotel : ");
            String hotelName = strScanner.nextLine();
            hotel.setName(hotelName);

            System.out.println("Choose a location from the following options:");
            for (Location loc : Location.values()) {
                System.out.println(loc.ordinal() + ". " + loc);
            }
            int locationChoice = scanner.nextInt();
            Location location = Location.values()[locationChoice];
            hotel.setLocation(location);

            System.out.println("Enter the number of floors: ");
            int floorNum = scanner.nextInt();
            hotel.setFloors(floorNum);

            System.out.println("Enter the number of rooms which each floor has: ");
            int roomCount = scanner.nextInt();
            hotel.setRoomsCount(roomCount);

            return true;
        }
    }

    @Override
    public boolean delete(String id) {
        showAll();

        System.out.println("Enter the ID of the hotel you want to delete");
        id=strScanner.nextLine();
        for (Hotel hotel : database.hotels) {
            if (hotel.getId().equals(id)){
                database.hotels.remove(hotel);
                return true;
            }
        }
        return false;
    }
}
