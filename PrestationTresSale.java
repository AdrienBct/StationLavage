/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetgroupe9;

/**
 *
 * @author doria
 */
public class PrestationTresSale extends Prestation {
    private int typeSalissure; 

    public PrestationTresSale(char categorie, int typeSalissure) {
        super(categorie);
        this.typeSalissure = typeSalissure;
    }

    private double getSurcout() {
        switch (typeSalissure) {
            case 1: return 5.0;  
            case 2: return 10.0; 
            case 3: return 15.0; 
            default: return 5.0;
        }
    }

    @Override
    public double nettoyage() {
        // Base Sale + Surcoût produit
        return lavage() + sechage() + prelavage() + interieur() + getSurcout();
    }

    @Override
    public String toString() {
        return "Très Sale type " + typeSalissure + " (" + super.toString() + ")";
    }
    

}