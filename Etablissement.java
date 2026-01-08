/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetgroupe9;

/**
 *
 * @author doria
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner; 

public class Etablissement {
    
    // Attributs
    private String nom;
    
    // Gestion des clients
    private Client[] tabClients;
    private int nbClients;      
    private final int MAX_CLIENTS = 100; 
    private RendezVous[][] tabRdv; 
    private final int NB_CRENEAUX = 16; 
    private final int NB_JOURS = 7;

    // Constructeur
    public Etablissement(String nom) {
        this.nom = nom;
        this.tabClients = new Client[MAX_CLIENTS];
        this.nbClients = 0;
        this.tabRdv = new RendezVous[NB_CRENEAUX][NB_JOURS];
    }

    //Affichage
    @Override
    public String toString() {
        return "Etablissement : " + nom + "\n" +
               "Nombre de clients enregistrés : " + nbClients + "\n" +
               "Capacité du planning : " + NB_JOURS + " jours sur " + NB_CRENEAUX + " créneaux.";
    }


    public int getNbClients() {
        return nbClients;
    }


// Q3.a
public Client rechercher(String nom, String numTel) {
    // On parcourt le tableau
    for (int i = 0; i < nbClients; i++) {
        Client c = tabClients[i];
        // On vérifie si le nom ET le numéro correspondent
        if (c.getNom().equals(nom) && c.getNumTel().equals(numTel)) {
            return c; 
        }
    }
    return null;
}
// 3(c)
    public Client ajouter(String nom, String numTel) {
        int id = nbClients + 1;
        Client nouveau = new Client(id, nom, numTel);
        insererClient(nouveau);
        return nouveau;
    }

    // 3(c) version surcharge 
    public Client ajouter(String nom, String numTel, String email) {
        int id = nbClients + 1;
        Client nouveau = new Client(id, nom, numTel, email);
        
        insererClient(nouveau);
        return nouveau;
    }


    private void insererClient(Client nouveau) {
        if (nbClients >= MAX_CLIENTS) {
            System.out.println("Erreur:Plus de place pour de nouveau clients");
            return;
        }
        int index = 0;
        // On avance tant qu'on n'est pas au bout ET que le client à cette case doit être avant le nouveau
        while (index < nbClients && !tabClients[index].placerApres(nouveau)) {
            index++;
        }
        
        // On décale tout le monde vers la droite à partir de index pour libérer la case
        for (int i = nbClients; i > index; i--) {
            tabClients[i] = tabClients[i - 1];
        }
        // On insère le client dans la case liberer
        tabClients[index] = nouveau;
        nbClients++; 
    }

    private int getIndiceJour(LocalDate date) {
        long joursEcart = LocalDate.now().until(date, ChronoUnit.DAYS);
        return (int) joursEcart;
    }


    private int getIndiceHeure(LocalTime heure) {
        long minutesEcart = LocalTime.of(10, 0).until(heure, ChronoUnit.MINUTES);
        // On divise par 30 pour avoir le nombre de créneaux
        return (int) minutesEcart / 30;
    }

    // 4(b)


    public LocalDateTime rechercherCreneauJour(LocalDate jourSouhaite) {
        Scanner sc = new Scanner(System.in);
        int col = getIndiceJour(jourSouhaite);
        if (col < 0 || col >= NB_JOURS) {
            System.out.println("Désolé cette date n'est pas dans le planning");
            return null;
        }
        System.out.println("Heures disponibles pour le " + jourSouhaite + " :");
        boolean auMoinsUn = false;

      
        for (int lig = 0; lig < NB_CRENEAUX; lig++) {
            if (tabRdv[lig][col] == null) { 
                LocalTime h = LocalTime.of(10, 0).plusMinutes(lig * 30);
                System.out.println("- " + h);
                auMoinsUn = true;
            }
        }

        if (!auMoinsUn) {
            System.out.println("Aucun créneau libre ce jour la");
            return null;
        }

        // Choix du client
        System.out.println("Quelle heure choisissez vous ? (Format HH:mm ex: 10:30)");
        String saisie = sc.nextLine();
        LocalTime heureChoisie = LocalTime.parse(saisie); // Conversion String en LocalTime


        int ligChoisie = getIndiceHeure(heureChoisie);
        if (tabRdv[ligChoisie][col] != null) {
            System.out.println("Erreur: Ce créneau n'est pas disponible");
            return null;
        }

        return LocalDateTime.of(jourSouhaite, heureChoisie);
    }

    public LocalDateTime rechercherCreneauHeure(LocalTime heureSouhaitee) {
        Scanner sc = new Scanner(System.in);
        int lig = getIndiceHeure(heureSouhaitee);

        // heure valide ?
        if (lig < 0 || lig >= NB_CRENEAUX) {
            System.out.println("L'établissement est fermé à cette heure (10h-17h).");
            return null;
        }

        System.out.println("Jours disponibles à " + heureSouhaitee + " :");
        boolean auMoinsUn = false;

        for (int col = 0; col < NB_JOURS; col++) {
            if (tabRdv[lig][col] == null) {
                LocalDate d = LocalDate.now().plusDays(col);
                System.out.println("- " + d);
                auMoinsUn = true;
            }
        }

        if (!auMoinsUn) {
            System.out.println("Aucun jour libre à cette heure");
            return null;
        }

        // Choix du client
        System.out.println("Quel jour choisissez-vous ? (Format AAAA-MM-JJ ex: 2025-01-24)");
        String saisie = sc.nextLine();
        LocalDate jourChoisi = LocalDate.parse(saisie);
        return LocalDateTime.of(jourChoisi, heureSouhaitee);
    }
    // 4(c)

    // Prestation EXPRESS
    public RendezVous ajouter(Client c, LocalDateTime dateHeure, char categorie, boolean lavInt) {
        int lig = getIndiceHeure(dateHeure.toLocalTime());
        int col = getIndiceJour(dateHeure.toLocalDate());

        if (lig < 0 || lig >= NB_CRENEAUX || col < 0 || col >= NB_JOURS) {
            System.out.println("Erreur: Date ou heure hors planning.");
            return null;
        }

        PrestationExpress p = new PrestationExpress(categorie, lavInt);

        RendezVous rdv = new RendezVous(c, p, dateHeure);
        tabRdv[lig][col] = rdv; // On remplit la case !

        return rdv;
    }

    public RendezVous ajouter(Client c, LocalDateTime dateHeure, char categorie) {
        int lig = getIndiceHeure(dateHeure.toLocalTime());
        int col = getIndiceJour(dateHeure.toLocalDate());

        if (lig < 0 || lig >= NB_CRENEAUX || col < 0 || col >= NB_JOURS) return null;

        PrestationSale p = new PrestationSale(categorie);
        
        RendezVous rdv = new RendezVous(c, p, dateHeure);
        tabRdv[lig][col] = rdv;

        return rdv;
    }

    //Prestation TRÈS SALE
    public RendezVous ajouter(Client c, LocalDateTime dateHeure, char categorie, int typeSalissure) {
        int lig = getIndiceHeure(dateHeure.toLocalTime());
        int col = getIndiceJour(dateHeure.toLocalDate());

        if (lig < 0 || lig >= NB_CRENEAUX || col < 0 || col >= NB_JOURS) return null;

        PrestationTresSale p = new PrestationTresSale(categorie, typeSalissure);
        
        RendezVous rdv = new RendezVous(c, p, dateHeure);
        tabRdv[lig][col] = rdv;

        return rdv;
    }
}
