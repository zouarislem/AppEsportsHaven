/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.User;
import utils.DataSource;
import utils.Statics;

/**
 *
 * @author aymen
 */
public class User_Service  {
    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<User> users;
    
    

    public User_Service() {
        request = DataSource.getInstance().getRequest();
    }
/*
   public boolean addUser(User user) {
     
        String url = Statics.BASE_URL + "api/users/new?username=" + user.getUsername()+ "&email=" + user.getMail()+ "&password=" + user.getPass()+ "&firstname=" + user.getFname()+ "&lastname=" + user.getLname()+ "&image=" + user.getImage()+ "&age=" + user.getAge()+ "&number=" + user.getNumber();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
   public boolean ModifierUser(User user,int id) {
     
        String url = Statics.BASE_URL + "api/users/update?username=" + user.getUsername()+ "&email=" + user.getMail()+ "&password=" + user.getPass()+ "&firstname=" + user.getFname()+ "&lastname=" + user.getLname()+ "&image=" + user.getImage()+ "&age=" + user.getAge()+ "&number=" + user.getNumber()+ "&id=" +id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
   */
        public ArrayList<User> Modifier_user(User u) {
        String url = Statics.BASE_URL + "user_mobile_edit/"+u.getId()+"/"+u.getNom()+"/"+u.getPrenom()+"/"+u.getPays()+"/"+u.getLogin()+"/"+u.getEmail()+"/"+u.getNumTel()+"/"+u.getMdp();

        request.setUrl(url);

        request.setPost(false);

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
       
                users = parseusers(new String(request.getResponseData()));
           
                request.removeResponseListener(this);
    
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return users;
    }
        
        
           public ArrayList<User> banne_user(int id , boolean ban) {
        String url = Statics.BASE_URL + "user_mobile_banne/"+id+"/"+ban;

        request.setUrl(url);

        request.setPost(false);

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
       
                users = parseusers(new String(request.getResponseData()));
           
                request.removeResponseListener(this);
    
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return users;
    }
        
        
     
        public ArrayList<User> add_user(User u) {
        String url = Statics.BASE_URL + "user_mobile_add/"+u.getNom()+"/"+u.getPrenom()+"/"+u.getPays()+"/"+u.getLogin()+"/"+u.getEmail()+"/"+u.getNumTel()+"/"+u.getMdp()+"/"+u.getRoles();

        request.setUrl(url);

        request.setPost(false);

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
       
                users = parseusers(new String(request.getResponseData()));
           
                request.removeResponseListener(this);
    
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return users;
    }
           
        
        
        
        
    public ArrayList<User> getAllusers() {
        String url = Statics.BASE_URL + "user_mobile";

        request.setUrl(url);

        request.setPost(false);

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
       
                users = parseusers(new String(request.getResponseData()));
           
                request.removeResponseListener(this);
    
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return users;
    }
    public ArrayList<User> delete_user(int id) {
        String url = Statics.BASE_URL + "user_mobile_delete/"+id;

        request.setUrl(url);

        request.setPost(false);

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
       
                users = parseusers(new String(request.getResponseData()));
           
                request.removeResponseListener(this);
    
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return users;
    }
    public ArrayList<User> parseusers(String jsonText) {
        try {
            users = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
              
                int id = (int)Float.parseFloat(obj.get("id").toString());
            
        
                String login = obj.get("login").toString();
               
                String mdp = obj.get("mdp").toString();
              
                  String nom = obj.get("nom").toString();
           
                  int numTel = (int)Float.parseFloat(obj.get("numTel").toString());
       
                   String prenom = obj.get("prenom").toString();
       
              
                    String email = obj.get("email").toString();
                    
                    
                    
    String pays = obj.get("pays").toString();
    String roles = obj.get("roles").toString();
    
    
    
                   String valeur_etat =obj.get("etat").toString();
                     int etat  =  0;
             

                     
                   if ("true".equals(valeur_etat))
                   {
                       etat  =  1;
                   }
                      String valeur_banne =obj.get("banne").toString();
                     int banne  =  0;
             

                     
                   if ("true".equals(valeur_banne))
                   {
                       banne  =  1;
                   }
                       
                 
                users.add(new User(id, login, mdp, nom, prenom, email, numTel, pays, roles, etat == 1 , banne == 1  ));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return users;
    }
 
      public User  get_User(int id)
      {
          for( User u :getAllusers()  )
          {
              if (u.getId()==id)
              {
                  return u;
              }
          }
          return null;
  
      }
      /*
      public boolean DesactiverUser(int id) 
      {
            String url = Statics.BASE_URL + "api/users/desactiver?id=" + id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;  
      }

      
       public boolean activerUser(int id) 
      {
            String url = Statics.BASE_URL + "api/users/activer?id=" + id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;  
      }

*/
      
}

