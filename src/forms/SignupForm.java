/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import models.Session;
import models.User;
import services.User_Service;

/**
 *
 * @author 21654
 */
public class SignupForm extends Form {

    Form h = this;
    Resources theme;

    public SignupForm() {
        
                super( "Register", BoxLayout.y());
        
          TextField email = new TextField("", "email", 5, TextArea.ANY);
           
            TextField login =  new TextField("", "login", 5, TextArea.ANY);
          
            TextField nom =  new TextField("", "nom", 5, TextArea.ANY);
     
            TextField pays =  new TextField("", "pays", 5, TextArea.ANY);

            TextField prenom = new TextField("", "prenom", 5, TextArea.ANY);
   
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
            this.add("USername : ").add(login);
            this.add("pass : ").add(pass);

            this.add("email : ").add(email);
            this.add("nom : ").add(nom);
            this.add("prenom : ").add(prenom);
            this.add("tel : ").add(tel);
            this.add("pays : ").add(pays);

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
              u.setRoles("ROLE_USER");
              new User_Service().add_user(u);
              new LoginForm().showBack();
            
                }

            });
                Button btnLogin = new Button("Sign in");
      btnLogin.addActionListener(l
                -> {
            new LoginForm().showBack();
        }
        );
            this.add(submit).add(btnLogin);
            this.show();
    }


      

}
