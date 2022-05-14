/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Asus
 */
public class Sponsors {
    
     private int idSponsor;
    private String societe;
        private String nomSponsor;
    private int montant;
    
    private int dureeSpons;
    
        private String typeSponsor;
        private String image;

    public int getIdSponsor() {
        return idSponsor;
    }

    public void setIdSponsor(int idSponsor) {
        this.idSponsor = idSponsor;
    }

  
    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getNomSponsor() {
        return nomSponsor;
    }

    public void setNomSponsor(String nomSponsor) {
        this.nomSponsor = nomSponsor;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getDureeSpons() {
        return dureeSpons;
    }

    public void setDureeSpons(int dureeSpons) {
        this.dureeSpons = dureeSpons;
    }

    public String getTypeSponsor() {
        return typeSponsor;
    }

    public void setTypeSponsor(String typeSponsor) {
        this.typeSponsor = typeSponsor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
