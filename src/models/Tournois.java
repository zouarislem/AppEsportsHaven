/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;


import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Tournois {
   private int id;
   private String nom,jeux;
   private int nbEquipes;
   private String dateDeb;
   private String dateFin;
   
   public Tournois(){
       
   }

    public Tournois(int id, String nom, String jeux, int nbEquipes, String dateDeb, String dateFin) {
        this.id = id;
        this.nom = nom;
        this.jeux = jeux;
        this.nbEquipes = nbEquipes;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }

    public Tournois(String nom, String jeux, int nbEquipes, String dateDeb, String dateFin) {
        this.nom = nom;
        this.jeux = jeux;
        this.nbEquipes = nbEquipes;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getJeux() {
        return jeux;
    }

    public int getNbEquipes() {
        return nbEquipes;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setJeux(String jeux) {
        this.jeux = jeux;
    }

    public void setNbEquipes(int nbEquipes) {
        this.nbEquipes = nbEquipes;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
  
}
