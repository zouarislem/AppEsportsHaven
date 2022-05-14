/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.codescan.CodeScanner;
import com.codename1.codescan.ScanResult;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

import models.Session;
/**
 *
 * @author aymen
 */
public class LoginForm extends Form {

    Form h = this;
    Resources theme;

    public LoginForm() {
        super( "Login", BoxLayout.y());

        String url = "http://localhost:8080/mobile/cnx.php";
        theme = UIManager.initFirstTheme("/theme");
        TextField txtn, txtpass;
        Button btnvalid, btninscrire;

        Label logi = new Label("ACCOUNT LOGIN");

        Label labUser = new Label("Email");

        txtn = new TextField("", "Email", 5, TextArea.ANY);

        Label labpASSWORD = new Label("PASSWORD");

        txtpass = new TextField("", "Password", 5, TextArea.PASSWORD);

        btnvalid = new Button("Valider");

        btninscrire = new Button("Sign up");

        this.add(logi).add(labUser).add(txtn).add(labpASSWORD).add(txtpass).add(btnvalid).add(btninscrire);

       

        btninscrire.addActionListener(l
                -> {
            new SignupForm().show();
        }
        );

        btnvalid.addActionListener(e -> {
            ConnectionRequest cnreq = new ConnectionRequest();
            cnreq.setPost(false);
            cnreq.addArgument("name", txtn.getText());
            cnreq.addArgument("password", txtpass.getText());
            cnreq.setUrl(url);
            cnreq.addResponseListener(ev -> {
     
                String chaine = new String(cnreq.getResponseData());
              
                System.out.println(chaine);
                if (chaine.equalsIgnoreCase("-1")) {

                    Dialog.show("Erreur", "Verifier votre Email and password", "OK", null);

                } else {
                    int id = Integer.valueOf(chaine);
                    Session.start(id);
                    System.out.println(Session.get());

                    if (Session.get().isEtat()) {
                        
                         if (!Session.get().isBanne()) {
                             
                                  Dialog.show("Erreur", "Compte Banne", "OK", null);   
                         }
                         else 
                         {
                         
                        if (Session.get().getRoles().equals("ROLE_USER")) {

             
new User_interface_form(h).show();
                        } else {
new List_Users_admin(h).show();
                  

                        }    
                         }


                    } else {
                        Dialog.show("Erreur", "Compte Desactive", "OK", null);
                    }

                }

            });
            NetworkManager.getInstance().addToQueueAndWait(cnreq);
        });

    }

}
