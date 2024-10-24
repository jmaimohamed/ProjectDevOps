<<<<<<< HEAD
package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {

    public List<Reservation> retrieveAllReservations();
    public Reservation retrieveReservation(String reservationId);
    public Reservation addReservation(Reservation r);
    public void removeReservation(String reservationId);
    public Reservation modifyReservation(Reservation reservation);

    // Here we will add later methods calling keywords and methods calling JPQL
    public List<Reservation> trouverResSelonDateEtStatus(Date d, boolean b);
}
=======
package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {

    public List<Reservation> retrieveAllReservations();
    public Reservation retrieveReservation(String reservationId);
    public Reservation addReservation(Reservation r);
    public void removeReservation(String reservationId);
    public Reservation modifyReservation(Reservation reservation);

    // Here we will add later methods calling keywords and methods calling JPQL
    public List<Reservation> trouverResSelonDateEtStatus(Date d, boolean b);
}
>>>>>>> 04f219e (Ajout des modifications Mockito et Junit)
