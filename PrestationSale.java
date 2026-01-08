/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetgroupe9;

/**
 *
 * @author doria
 */
public class PrestationSale extends Prestation {

    public PrestationSale(char categorie) {
        super(categorie);
    }

    @Override
    public double nettoyage() {
        return lavage() + sechage() + prelavage() + interieur();
    }

    @Override
    public String toString() {
        return "Sale (" + super.toString() + ")";
    }
    
  
}