/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetgroupe9;

/**
 *
 * @author doria
 */
public class PrestationExpress extends Prestation {
    private boolean lavageInterieur;

    public PrestationExpress(char categorie, boolean lavageInterieur) {
        super(categorie);
        this.lavageInterieur = lavageInterieur;
    }

    @Override
    public double nettoyage() {
        double total = lavage() + sechage();
        if (lavageInterieur) {
            total += interieur();
        }
        return total;
    }
    
    @Override
    public String toString() {
        return "Express (" + super.toString() + ") - Intérieur: " + (lavageInterieur ? "Oui" : "Non");
    }
    
   
}