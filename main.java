/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetgroupe9;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author doria
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("      TEST PROJET STATION DE LAVAGE      ");
        System.out.println("--------------------------------------------");


        // creaton etablissement
        System.out.println("\n ceaation etablissement");
        Etablissement station = new Etablissement("Lavage Auto Efrei");
        System.out.println(station.toString());


        // verif des prix

        System.out.println("\n verif des prix");

        // Test 1 : sans intérieur cat A
        PrestationExpress p1 = new PrestationExpress('A', false);
        System.out.println("-> " + p1.toString());

        System.out.println("   Prix : " + p1.nettoyage() + " € (Attendu : 30.0)");

        // Test 2  Sale Cat B
        PrestationSale p2 = new PrestationSale('B');
        System.out.println("-> " + p2.toString());
        System.out.println("   Prix : " + p2.nettoyage() + " € (Attendu : 78.0)");

        // Test 3  Très Sale cat C Type 2 Boue
        PrestationTresSale p3 = new PrestationTresSale('C', 2);
        System.out.println("-> " + p3.toString());
        System.out.println("   Prix : " + p3.nettoyage() + " € (Attendu : 104.75)");


        // creation client

        System.out.println("\ncreation client");
        
        System.out.println("Ajout de Zola (fin)");
        station.ajouter("Zola", "0649930003"); 
        
        System.out.println("Ajout de Dorian (début)");
        station.ajouter("Arthur", "0603840001");
        
        System.out.println("Ajout de Clement (milieu)");
        Client cMartin = station.ajouter("Martin", "0603895902", "clement@email.com");

        System.out.println("Nombre total de clients : " + station.getNbClients());
        
        // test recherche

        System.out.println("\n test recherche");
        
        Client cTrouve = station.rechercher("Clement", "0603895902");
        if (cTrouve != null) {
            System.out.println("Succès : Client trouvé -> " + cTrouve.toString());
        } else {
            System.out.println("Erreur : Client non trouvé");
        }

        // 5. Qestion 4b

        System.out.println("\nQestion 4b");

        
        // Test A  Chercher une heure pour ajd
        System.out.println("\n Test A  Chercher une heure pour ajd");
        LocalDateTime choixJour = station.rechercherCreneauJour(LocalDate.now());
        if (choixJour != null) {
            System.out.println(">> Tu as choisi : " + choixJour);
        }

        // Test B  Chercher un jour pour 14h00
        System.out.println("\nTest B Jours dispos pour 14h00");
        LocalDateTime choixHeure = station.rechercherCreneauHeure(LocalTime.of(14, 0));
        if (choixHeure != null) {
            System.out.println(">> Tu as choisi : " + choixHeure);
        }

        //QUESTION 4c

        System.out.println("\n=QUESTION 4c");
        
        // On force l'ajout d'un RDV pour Clement, aujourd'hui à 15h00, Express, Cat A, Intérieur inclus
        LocalDateTime dateRdv = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0));
        RendezVous rdvCree = station.ajouter(cMartin, dateRdv, 'A', true);

        if (rdvCree != null) {
            System.out.println("RDV Confirmé");
            System.out.println(rdvCree.toString());
            System.out.println("Prix prestation : " + rdvCree.getPrestation().nettoyage() + " €");
        } else {
            System.out.println("Erreur :Impossible d'ajouter le RDV ");
        }
        
    }
    
}
