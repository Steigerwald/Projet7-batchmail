package Projet7.batchMail;

import lombok.Data;
import java.util.Date;

@Data
public class Reservation {

    private int idReservation;
    private String etatReservation;
    private Date dateReservation;
    private Date dateDeRetrait;
    private Date dateDeRetour;
    private int delaiDeLocation;
    private Boolean prolongation;
    private Boolean isactif;
    private User user;
    private Livre livre;
}
