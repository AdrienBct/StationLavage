/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetgroupe9;

/**
 *
 * @author doria
 */
public class Client {
    // Attributs privés
    private int numero;
    private String nom;
    private String numTel;
    private String email;

    // Constructeur 
    public Client(int numero, String nom, String numTel) {
        this.numero = numero;
        this.nom = nom;
        this.numTel = numTel;
        this.email = ""; // On met une chaine vide si pas demail
    }

    public Client(int numero, String nom, String numTel, String email) {
        this.numero = numero;
        this.nom = nom;
        this.numTel = numTel;
        this.email = email;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public String getNumTel() {
        return numTel;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public String getEmail() {
        return email;
    }

    // Méthode pour savoir si ce client doit être placé après un autre

    public boolean placerApres(Client c) {
        if (this.nom.compareTo(c.nom) > 0) {
            return true;
        } else {
            return false;
        }
    }
    
  

 
    @Override
    public String toString() {
        return "Client n°" + numero + " : " + nom + " (" + numTel + ") " + email;
    }
}
