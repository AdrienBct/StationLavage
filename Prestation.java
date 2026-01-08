/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetgroupe9;

/**
 *
 * @author doria
 */
public class Prestation {
 
    protected char categorie; // A, B ou C

    public Prestation(char categorie) {
        this.categorie = categorie;
    }

    // Calcul du prix du LAVAGE
    public double lavage() {
        double prixBase = 20.0;
        if (categorie == 'B') return prixBase + (prixBase * 0.50);
        if (categorie == 'C') return prixBase + (prixBase * 0.75);
        return prixBase;
    }

    // Calcul du prix du SÉCHAGE 
    public double sechage() {
        double prixBase = 10.0;
        if (categorie == 'B') return prixBase + (prixBase * 0.05);
        if (categorie == 'C') return prixBase + (prixBase * 0.10);
        return prixBase;
    }

    // Calcul du prix du PRÉLAVAGE
    public double prelavage() {
        double prixBase = 5.0;
        if (categorie == 'B') return prixBase + (prixBase * 0.50);
        if (categorie == 'C') return prixBase + (prixBase * 0.75);
        return prixBase;
    }

    // Calcul du prix INTÉRIEUR
    public double interieur() {
        if (categorie == 'C') return 40.0;
        return 30.0;
    }

    // Méthode NETTOYAGE 
    public double nettoyage() {
        return 0; 
    }
    
    public String toString() {
        return "Véhicule cat. " + categorie;
    }
    
   
}