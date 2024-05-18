package serviceClasses;

import entity.Reservation;

import java.time.LocalDate;
import java.util.List;

import static serviceInterfaces.Utils.*;

public class ReservationService implements serviceInterfaces.ReservationService {
    @Override
    public boolean addReservation(Reservation reservation) {
        return false;
    }

    @Override
    public Reservation showReservation(String id) {
        return null;
    }

    @Override
    public List<Reservation> showReservationByUser(String id) {
        return null;
    }

    @Override
    public List<Reservation> showReservationByHotel(String id) {
        return null;
    }

    @Override
    public boolean cancelReservation(String id) {
        return false;
    }

    @Override
    public boolean finishReservation(String id, LocalDate date) {
        return false;
    }

    @Override
    public boolean rescheduleReservation(String id, LocalDate from, LocalDate to) {
        return false;
    }
}
