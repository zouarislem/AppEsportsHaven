/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import models.Sponsors;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Service_Sponsors {
    
    
            
    public ArrayList<Sponsors> Coachs;
    public static Service_Sponsors instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Sponsors() {
        req = new ConnectionRequest();
    }

     
    public static Service_Sponsors getInstance() {
        if (instance == null) {
            instance = new Service_Sponsors();
        }
        return instance;
    }
    
    
    
    
     public ArrayList<Sponsors> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Sponsors coach = new Sponsors();
                
                
                

               float idSponsor = Float.parseFloat(obj.get("idSponsor").toString());
               coach.setIdSponsor((int) idSponsor);
               
       
                
                float montant = Float.parseFloat(obj.get("montant").toString());
               coach.setMontant((int) montant);
               
          
               
                float dureeSpons = Float.parseFloat(obj.get("dureeSpons").toString());
               coach.setDureeSpons((int) dureeSpons);
               
          
                  
                  
     
                
        
               coach.setSociete(obj.get("societe").toString());
               
               
               coach.setNomSponsor(obj.get("nomSponsor").toString());
               
               coach.setImage(obj.get("image").toString());

                 coach.setTypeSponsor(obj.get("typeSponsor").toString());


     


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Sponsors> findAll() {
        String url = Statics.BASE_URL + "sponsors_mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Coachs = parseCoach(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Coachs;
    }
    
    
    
    
    
    
    
    
    
    
    
    
       
          public void AddSponsors(Sponsors c) {
        String url = Statics.BASE_URL + "newsposnor_mobile/"+ c.getSociete()+ "/" + c.getNomSponsor()+ "/" +c.getMontant()+ "/" + c.getDureeSpons()+ "/" +c.getTypeSponsor()+ "/" + c.getImage(); //création de l'URL

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
   
    }
    
    
    
    
        
    public boolean deleteSponsor(int idSponsor) {
        String url = Statics.BASE_URL + "SupprimerSponsor?idSponsor=" + idSponsor;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
                    
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOK;
    }
    
    
    
    
    
    
    
    
    
    
     public boolean ModifierSponsor( Sponsors c) {
        
       String url = Statics.BASE_URL + "updateSponsor?idSponsor=" + c.getIdSponsor()+"&nomSponsor=" + c.getNomSponsor()+"&societe=" + c.getSociete()+"&montant=" + c.getMontant()+"&dureeSpons=" + c.getDureeSpons();

       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code success  http 200 ok
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        //System.out.println("url ==> " + url);
        //System.out.println("data ==> " + req);
        return resultOK;

    }

    
    
    
    
    
    
    
    
    
    
}
