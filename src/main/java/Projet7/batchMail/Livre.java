package Projet7.batchMail;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

@Data
public class Livre {

    private int idLivre;
    private String titre;
    private String auteur;
    private Date publication;
    private String resume;
    private String nombrePages;
    private String nomCategorie;
    private Date dateAchat;
    private int prixLocation;
    private String etatLivre;
    private Boolean disponibilite;
    private Bibliotheque bibliotheque;
}
