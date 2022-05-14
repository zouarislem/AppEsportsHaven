/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Tournois;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class TournoisService {
    public boolean resultOK;
    ArrayList<Tournois> listJeus;
    boolean verf;

    public TournoisService() {
    }
    
     public ArrayList<Tournois> parseUsers(String json) {
        
        ArrayList<Tournois> listJeus = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
           
            int i=0;
            for (Map<String, Object> obj : list) {
                Tournois u = new Tournois();
                // int id = Integer.parseInt(obj.get("id").toString());
                u.setId((int) Float.parseFloat(obj.get("id").toString()));
                u.setNom(obj.get("nom").toString());
                u.setNbEquipes((int) Float.parseFloat(obj.get("nbEquipes").toString()));
                u.setDateDeb(obj.get("dateDeb").toString());
                u.setDateFin(obj.get("dateFin").toString());
                u.setJeux(obj.get("jeux").toString());
                
                //u.setEquipe(obj.get("equipe").);
                //   u.setPic(obj.get("picture").toString());
                System.out.println(u);
                listJeus.add(u);

            }

        } catch (IOException ex) {
        }
        return listJeus;
    }
    
    public ArrayList<Tournois> getJeus() {
        listJeus = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/tournois/showJSON");
        
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                // UserService ser = new UserService();
                listJeus = parseUsers(new String(con.getResponseData()));

            }
        });
        System.out.println(listJeus);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listJeus;
    }
    
    public void desc(Tournois u) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion

        String Url = "http://127.0.0.1:8000/tournois/deleteJSON?id=" + u.getId();// création de l'URL
        System.out.println(Url);
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            System.out.println("c");
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
     public Tournois fetch(String nom) {
        TournoisService us = new TournoisService();
        Tournois usr = new Tournois();
        ArrayList<Tournois> listUsers = new ArrayList();
        listUsers = us.getJeus();
        for (Tournois u : listUsers) {
            if (u.getNom().equals(nom)) {
                usr.setId(u.getId());
                usr.setNom(u.getNom());
                usr.setNbEquipes(u.getNbEquipes());
                usr.setDateDeb(u.getDateDeb());
                usr.setDateFin(u.getDateFin());
                usr.setJeux(u.getJeux());
            }
        }
        
        return usr;
    }
//    public void update(Jeu u) {
//        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
//
//        String Url = "http://127.0.0.1:8000/updateGamesJSON?id=" + u.getId() + "&nom=" + u.getNom() + "&date=" +u.getDate();// création de l'URL
//        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
//        System.out.println(Url);
//        con.addResponseListener((e) -> {
//            System.out.println("c");
//            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
//            System.out.println(str);//Affichage de la réponse serveur sur la console
//
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
//    }
    
    public boolean Create(Tournois u) {
        boolean strr = false;
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://127.0.0.1:8000/tournois/add/new?nom=" + u.getNom() + "&nbEquipes=" + u.getNbEquipes() +  "&jeux=" + u.getJeux();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        System.out.println(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            //Affichage de la réponse serveur sur la console
            resultOK = con.getResponseCode() == 200;
            //con.removeResponseListener(this);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        return resultOK;
    }
}
