package serviceClasses;

import entity.Hotel;

import java.util.List;

import static serviceInterfaces.Utils.*;

public class HotelService implements serviceInterfaces.HotelService {
    @Override
    public void add(Hotel hotel) {

    }

    @Override
    public Hotel show(String id) {
        return null;
    }

    @Override
    public List<Hotel> showAll() {
        return null;
    }

    @Override
    public boolean edit(String id, Hotel hotel) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
