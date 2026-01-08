/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetgroupe9;

/**
 *
 * @author doria
 */
import java.time.LocalDateTime; 

public class RendezVous {
    // Attributs
    private Client client;
    private Prestation prestation;
    private LocalDateTime dateHeure;

    // Constructeur
    public RendezVous(Client client, Prestation prestation, LocalDateTime dateHeure) {
        this.client = client;
        this.prestation = prestation;
        this.dateHeure = dateHeure;
    }

    // Getters 
    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public Client getClient() {
        return client;
    }
    
    public Prestation getPrestation() {
        return prestation;
    }

    @Override
    public String toString() {
        // Affiche : "Le 2025-01-15T10:00 : Client n°1 (Dupont) - Prestation : Express..."
        return "Le " + dateHeure + " : " + client.toString() + " - Prestation : " + prestation.toString();
    }

  
}