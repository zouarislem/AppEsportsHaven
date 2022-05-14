/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import models.Sponsors;
import forms.BaseForm;
import forms.SponsorsForm;
import services.Service_Sponsors;
import java.io.IOException;
import java.util.Date;
import services.Service_Sponsors;

/**
 *
 * @author Asus
 */
public class AddSponsors extends BaseForm{
    String file ;
      String file2 ;
      Resources theme;
    
     public AddSponsors(Form previous) throws IOException {
      super("Sponsors", BoxLayout.y());
      theme = UIManager.initFirstTheme("/themeCoHeal");
 
Label AJOUT = new Label("ADD New Sponsor");
 this.add(AJOUT);

            
  TextField nom = new TextField("", "Nom", 20, TextArea.TEXT_CURSOR);
  
  TextField type = new TextField("", "type", 20, TextArea.TEXT_CURSOR);
  
  TextField societe = new TextField("", "societe", 20, TextArea.TEXT_CURSOR);
  
  TextField montant = new TextField("", "montant ", 20, TextArea.TEXT_CURSOR);
  TextField dureeSpons = new TextField("", "dureeSpons", 20, TextArea.TEXT_CURSOR);


        
       
        Button upload = new Button("Upload Image Sponsor");
        upload.setUIID("vtnvalid");
   
      Button save = new Button("Ajouter");
      
     
        
        this.add("Nom : ").add(nom);
        this.add("type : ").add(type);
        this.add("societe : ").add(societe);
        this.add("montant : ").add(montant);
        this.add("dureeSpons : ").add(dureeSpons);

               
                                
  
        this.add(upload);
        this.add(save);
        
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost/MobileProject/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    file=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    com.codename1.ui.Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });
        

        
        
    
        save.addActionListener(l
                                -> {

                            if (nom.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide de Name ", "OK", null);

                            }  else if (societe.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide societe  ", "OK", null);
                                
                            }else if (type.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide type  ", "OK", null);
                                
                            }
                            else if (montant.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Taper montant ", "OK", null);
                                
                            }
                             else if (dureeSpons.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Taper dureeSpons ", "OK", null);
                                
                            } 
                            
                            
                            
                            
                 else {
                                
                           
                                try {
                                  
                                
      
                                    
                                    Sponsors c = new Sponsors();
                                    
                                    
                                    c.setNomSponsor(nom.getText());
                                    
                                     c.setTypeSponsor(type.getText());
                                     c.setSociete(societe.getText());
                                     
                                     
                                    c.setMontant(Integer.valueOf(montant.getText()));
                                    c.setDureeSpons(Integer.valueOf(dureeSpons.getText()));

                                      c.setImage(file);

                         
                                    
           System.out.println("forms.addEvet.addItem()"+c);
           new Service_Sponsors().AddSponsors(c);
           com.codename1.ui.Dialog.show("Ajouter Sponsor", "Ajouter Sponsor aves success ", "OK", null);
                                        
                                        
                                 
                                        
  /////////////////////////////////////   Notification     /////////////////////
  
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Sponsor "+c.getNomSponsor()+"  ajoute avec succes");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("hallllllllllllllllllllllllllllllo");
////////////////////////////////////////////
                                        
                                } catch (Exception ex) {
                                       System.out.println("hekllllo");
                                }

                            }

                        }
                        );
        
        
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
      
        
        
        
        
        
        
        
        
           
           this.getToolbar().addCommandToLeftSideMenu("Back", null, ev -> {
               try {
                   new SponsorsForm(previous).showBack();
               } catch (Exception ex) {
            
               }
               
               
               
        });
        
        
           
           
           
           
           






           
        
 this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
        
        this.show();

             
     
     
                            

      
     
     }
     
     
     
     private void addButton(Image img, Resources res) {
         
         
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        add(cnt);  
        
		}
     
     
     
     
     private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overLay = new Label("", "ImageOverLay");

     
    }

}
