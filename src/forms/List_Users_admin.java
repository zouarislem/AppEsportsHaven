/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.ui.util.Resources;


import models.Session;
import models.Sponsors;
import models.User;
import services.User_Service;

/**
 *
 * @author 21654
 */
public class List_Users_admin extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");

    public List_Users_admin(Form previous) {
        super("Users",BoxLayout.y());

        this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                Label logi = new Label("LES PRODUITS");
                logi.setUIID("login");
              //  this.add(logi);

              
                 for (User c : new User_Service().getAllusers()) {

                    this.add(addItem_user(c));

                }

                this.revalidate();
            });
        });
           this.getToolbar().addCommandToOverflowMenu("Add ", null, ev -> {
                       Form ajout = new Form("Add",BoxLayout.y());
                           TextField email = new TextField("", "email", 5, TextArea.ANY);
           
            TextField login =  new TextField("", "login", 5, TextArea.ANY);
          
            TextField nom =  new TextField("", "nom", 5, TextArea.ANY);
     
            TextField pays =  new TextField("", "pays", 5, TextArea.ANY);

            TextField prenom = new TextField("", "prenom", 5, TextArea.ANY);
          TextField role = new TextField("", "role", 5, TextArea.ANY);
            TextField tel =  new TextField("", "tel", 5, TextArea.ANY);
      
            TextField pass = new TextField("", "Password", 20, TextArea.NUMERIC);

            String text_mail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            // val mail   
            Validator val_mail = new Validator();
            val_mail.addConstraint(email, new LengthConstraint(8));
            val_mail.addConstraint(email, new RegexConstraint(text_mail, ""));

            Validator val_login = new Validator();

            val_login.addConstraint(login, new LengthConstraint(8));

            String text_saisir_des_caracteres = "^[0-9]+$";

            val_login.addConstraint(login, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_prenom = new Validator();

            val_prenom.addConstraint(prenom, new LengthConstraint(8));

            val_prenom.addConstraint(prenom, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_nom = new Validator();

            val_nom.addConstraint(nom, new LengthConstraint(8));

            val_nom.addConstraint(nom, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_pays = new Validator();

            val_pays.addConstraint(pays, new LengthConstraint(8));

            val_pays.addConstraint(pays, new RegexConstraint(text_saisir_des_caracteres, ""));
            Button submit = new Button("Submit");
            ajout.add("USername : ").add(login);
            ajout.add("pass : ").add(pass);

            ajout.add("email : ").add(email);
            ajout.add("nom : ").add(nom);
            ajout.add("prenom : ").add(prenom);
            ajout.add("tel : ").add(tel);
            ajout.add("pays : ").add(pays);
             ajout.add("role : ").add(role);
            

            submit.addActionListener(lll
                    -> {
                if (login.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de USername ", "OK", null);

                } else if (val_login.isValid()) {
                    Dialog.show("Erreur USername !", "il faut saisir des caracteres  !", "OK", null);
                } else if (pass.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de pass ", "OK", null);

                } else if (email.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                } else if (!val_mail.isValid()) {
                    Dialog.show("Erreur EMAIL !", "email incorrect", "OK", null);
                } else if (nom.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de nom ", "OK", null);

                } else if (val_nom.isValid()) {
                    Dialog.show("Erreur nom !", "nom incorrect", "OK", null);
                } else if (prenom.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de prenom ", "OK", null);

                } else if (val_prenom.isValid()) {
                    Dialog.show("Erreur nom !", "prenom incorrect", "OK", null);
                } else if (tel.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de tel ", "OK", null);

                } else if (pays.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de pays ", "OK", null);

                } else if (val_pays.isValid()) {
                    Dialog.show("Erreur nom !", "pays incorrect", "OK", null);
                } else {
                    User u = new User();
                    
                    u.setLogin(login.getText());
  u.setMdp(pass.getText());
   u.setEmail(email.getText());
    u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
          u.setPays(pays.getText());
              u.setNumTel(Integer.valueOf(tel.getText()));
              u.setId(Session.get().getId());
              u.setRoles(role.getText());
              new User_Service().add_user(u);
 new List_Users_admin(previous).showBack();
            
                }

            });
                     ajout.getToolbar().addCommandToLeftBar("back",null, evx -> {
                this.showBack();
            });
        
            ajout.add(submit);
            ajout.show();

           });

        this.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : this.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : this.getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
        
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);
        
 
      
       this.getToolbar().addCommandToSideMenu("Profile", null, e -> {

            Form Profile = new Form("Profile", BoxLayout.y());

            Profile.getToolbar().addCommandToLeftBar("back", null, (evt) -> {
                this.showBack();
            });
            AutoCompleteTextField email = new AutoCompleteTextField(Session.get().getEmail());
            email.setMinimumElementsShownInPopup(1);
            AutoCompleteTextField login = new AutoCompleteTextField(Session.get().getLogin());
            login.setMinimumElementsShownInPopup(1);
            AutoCompleteTextField nom = new AutoCompleteTextField(Session.get().getNom());
            nom.setMinimumElementsShownInPopup(1);
            AutoCompleteTextField pays = new AutoCompleteTextField(Session.get().getPays());
            pays.setMinimumElementsShownInPopup(1);
            AutoCompleteTextField prenom = new AutoCompleteTextField(Session.get().getPrenom());
            prenom.setMinimumElementsShownInPopup(1);
            AutoCompleteTextField tel = new AutoCompleteTextField(String.valueOf(Session.get().getNumTel()));
            tel.setMinimumElementsShownInPopup(1);
            TextField pass = new TextField("", "Password", 20, TextArea.NUMERIC);

            String text_mail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            // val mail   
            Validator val_mail = new Validator();
            val_mail.addConstraint(email, new LengthConstraint(8));
            val_mail.addConstraint(email, new RegexConstraint(text_mail, ""));

            Validator val_login = new Validator();

            val_login.addConstraint(login, new LengthConstraint(8));

            String text_saisir_des_caracteres = "^[0-9]+$";

            val_login.addConstraint(login, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_prenom = new Validator();

            val_prenom.addConstraint(prenom, new LengthConstraint(8));

            val_prenom.addConstraint(prenom, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_nom = new Validator();

            val_nom.addConstraint(nom, new LengthConstraint(8));

            val_nom.addConstraint(nom, new RegexConstraint(text_saisir_des_caracteres, ""));

            Validator val_pays = new Validator();

            val_pays.addConstraint(pays, new LengthConstraint(8));

            val_pays.addConstraint(pays, new RegexConstraint(text_saisir_des_caracteres, ""));
            Button submit = new Button("Submit");
            Profile.add("USername : ").add(login);
            Profile.add("pass : ").add(pass);

            Profile.add("email : ").add(email);
            Profile.add("nom : ").add(nom);
            Profile.add("prenom : ").add(prenom);
            Profile.add("tel : ").add(tel);
            Profile.add("pays : ").add(pays);

            submit.addActionListener(lll
                    -> {
                if (login.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de USername ", "OK", null);

                } else if (val_login.isValid()) {
                    Dialog.show("Erreur USername !", "il faut saisir des caracteres  !", "OK", null);
                } else if (pass.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de pass ", "OK", null);

                } else if (email.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de email ", "OK", null);

                } else if (!val_mail.isValid()) {
                    Dialog.show("Erreur EMAIL !", "email incorrect", "OK", null);
                } else if (nom.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de nom ", "OK", null);

                } else if (val_nom.isValid()) {
                    Dialog.show("Erreur nom !", "nom incorrect", "OK", null);
                } else if (prenom.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de prenom ", "OK", null);

                } else if (val_prenom.isValid()) {
                    Dialog.show("Erreur nom !", "prenom incorrect", "OK", null);
                } else if (tel.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de tel ", "OK", null);

                } else if (pays.getText().equals("")) {
                    Dialog.show("Erreur", "Champ vide de pays ", "OK", null);

                } else if (val_pays.isValid()) {
                    Dialog.show("Erreur nom !", "pays incorrect", "OK", null);
                } else {
                    User u = new User();
                    
                    u.setLogin(login.getText());
  u.setMdp(pass.getText());
   u.setEmail(email.getText());
    u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
          u.setPays(pays.getText());
              u.setNumTel(Integer.valueOf(tel.getText()));
              u.setId(Session.get().getId());
              new User_Service().Modifier_user(u);
                try {
                Session.close();
             Session.start(u.getId());
             this.showBack();
            } catch (Exception ex) {

            }
                }

            });

            Profile.add(submit);
            Profile.show();
        });
       Resources res = null;
       this.getToolbar().addCommandToSideMenu("Utilisateurs", null, e -> {
            try {
              
                new List_Users_admin(previous).show();
            } catch (Exception ex) {

            }

        });
       this.getToolbar().addCommandToSideMenu("Sponsor Managment", null, e -> {
            try {
              
                new SponsorsForm(previous).show();
            } catch (Exception ex) {

            }

        });
        
       this.getToolbar().addCommandToSideMenu("Liste Des Sponsors", null, e -> {
            try {
              
                new ListeSponsors().show();
            } catch (Exception ex) {

            }

        });
       this.getToolbar().addCommandToSideMenu("Liste des Tournois", null, e -> {
            try {
              
                new JeuForm().show();
            } catch (Exception ex) {

            }

        });
       
        this.getToolbar().addCommandToSideMenu("Logout", null, e -> {
            try {
                Session.close();
                      new LoginForm().showBack();
            } catch (Exception ex) {

            }
      
        });
        

    }
   String Image ="";

    
    public MultiButton addItem_user(User c) {
        MultiButton m = new MultiButton();

      
       //m.setUIIDLine1(url);
        m.setTextLine1(c.getLogin());
        m.setTextLine2(c.getEmail());
        
          
        m.setEmblem(theme.getImage("round.png"));
  
    m.addActionListener(l
                -> {

           Form f2 = new Form("DETAILS",BoxLayout.y());
               String url = "http://localhost:8080/mobile/qrcode.php";

                ConnectionRequest cnreq = new ConnectionRequest();
                cnreq.setPost(false);
                String data = "Nom : " + c.getNom()+ " Prenom : " + c.getPrenom()+ "  Email :" + c.getEmail()+ " Username : " + c.getLogin() + "Merci pour votre confiance &#128525;";

                cnreq.addArgument("data", data);
                cnreq.setUrl(url);
                cnreq.addResponseListener(evx
                        -> {
                     Image = new String(cnreq.getResponseData());
                 

                }
                );
                NetworkManager.getInstance().addToQueueAndWait(cnreq);
 String urlqr = "http://localhost:8080/mobile/"+Image;
   Image imge;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        imge = URLImage.createToStorage(enc, urlqr, urlqr);

            Button supp = new Button("Supprimer");
    
              supp.addActionListener(xxx
                    -> {
                 new User_Service().delete_user(c.getId());
               new List_Users_admin(this).show();
            }
            );
          
            Button ban ;
            
            if (c.isBanne())
            {
              ban = new Button("Banner") ; 
            }
            else {
                ban = new Button("DeBanner");
            }
 
              supp.addActionListener(xxx
                    -> {
                 new User_Service().delete_user(c.getId());
               new List_Users_admin(this).show();
            }
            );
                ban.addActionListener(xxx
                    -> {
                       if (c.isBanne())
            {
             new User_Service().banne_user(c.getId(),true);
            }
            else {
                   new User_Service().banne_user(c.getId(),false);
            }
    
               new List_Users_admin(this).show();
            }
            );

            f2.getToolbar().addCommandToLeftBar("back",null, (evt) -> {
                this.showBack();
            });
             f2.add("nom : ").add(c.getNom()).add("Prenom : ").add(c.getPrenom()).add("pays : ").add(c.getPays()).add("Tel ;").add(String.valueOf(c.getNumTel())).add("email : ").add(c.getEmail()).add("Login : ").add(c.getLogin()).add(imge).add(supp).add(ban);
         
            f2.show();

        }  );
      
        return m;
    }

}
