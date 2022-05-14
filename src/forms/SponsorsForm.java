/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import models.Sponsors;
import services.Service_Sponsors;
import java.io.IOException;
import java.io.OutputStream;
import models.Session;

/**
 *
 * @author Asus
 */
public class SponsorsForm extends BaseForm{
     
    
    Resources theme = UIManager.initFirstTheme("/themeCoHeal");
    public SponsorsForm(Form previous)
    {
     
     
           super("Sponsors",BoxLayout.y());
                 this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                
                
             for (Sponsors c : new Service_Sponsors().findAll()) {

            this.add(addItem_Coach(c,previous));

        }
               this.revalidate();
            });
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
                    mb.setUIIDLine1("libC");
                    mb.setUIIDLine2("btn");
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);  
        Resources res = null;
          this.getToolbar().addCommandToRightBar("add", null, ev -> {
               try {
                new AddSponsors(this).show();
               } catch (Exception ex) {
            
               }             
        });
         this.getToolbar().addCommandToSideMenu("Utilisateurs", null, e -> {
            try {
              
                new List_Users_admin(previous).show();
            } catch (Exception ex) {

            }

        });
       this.getToolbar().addCommandToSideMenu("Sponsors Management", null, e -> {
            try {
                new SponsorsForm(previous).show();
              
                
            } catch (Exception ex) {

            }

        });
       this.getToolbar().addCommandToSideMenu("Liste Des Sponsors", null, e -> {
            try {
              new ListeSponsors(res).show();
                
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

    SponsorsForm(Resources res,Form previous) {
        super("Sponsors",BoxLayout.y());
                 this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                
                
             for (Sponsors c : new Service_Sponsors().findAll()) {

            this.add(addItem_Coach(c,previous));

        }
               this.revalidate();
            });
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
                    mb.setUIIDLine1("libC");
                    mb.setUIIDLine2("btn");
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);     
          this.getToolbar().addCommandToRightBar("add", null, ev -> {
               try {
                new AddSponsors(this).show();
               } catch (Exception ex) {
            
               }             
        });
          Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        
        tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_UPDATE, e -> new NewsfeedForm(res).show());
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res, previous).show());
        tb.addMaterialCommandToSideMenu("Sponsors Management", FontImage.MATERIAL_EXIT_TO_APP, e -> new SponsorsForm(previous).show());
        tb.addMaterialCommandToSideMenu("Liste Des Sponsors", FontImage.MATERIAL_EXIT_TO_APP, e -> new ListeSponsors(res).show());
    }

    
    
 
// 
    
     public Container addItem_Cotch_detail(Sponsors c) {
  String url = "http://127.0.0.1:8000/uploads/images/" + c.getImage();

            ImageViewer image_coach;
            Image imge;
            EncodedImage enc;
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge = URLImage.createToStorage(enc, url, url);
            
                 image_coach = new ImageViewer(imge);
        
        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        
        Label nom = new Label("Nom  : "+c.getNomSponsor());          
       Label image = new Label("image : "+String.valueOf(c.getImage()));
        

       
       Label socciete = new Label("societe  : "+c.getSociete());   
       
       
       Label montant = new Label("montant  : "+c.getMontant());       
       Label duree = new Label("dureeSpons  : "+c.getDureeSpons());       

              Label type = new Label("typeSponsor  : "+c.getDureeSpons());       
         
        Button screen = new Button("Screen");

        cn2.add(nom).add(image).add(socciete).add(montant).add(duree).add(type).add(screen);
        cn1.add(BorderLayout.WEST, cn2);
        

        screen.addActionListener(e -> {
            
             Form form = Display.getInstance().getCurrent();
        if (form != null) {
            
            Image screenshot = Image.createImage(form.getWidth(), form.getHeight());
form.revalidate();
form.setVisible(true);
form.paintComponent(screenshot.getGraphics(), true);

String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
} catch(IOException err) {
    Log.e(err);
}
        }  
            
            });
 
      
        return cn1;

    }
     
     
   public MultiButton  addItem_Coach(Sponsors c,Form previous) {
     
          MultiButton m = new MultiButton();
          
/////////////////////////////////////   Notification     /////////////////////
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Liste des Sponsors");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("Hallo");
///////////////////////////////////////////

                   
               

  String url = "http://127.0.0.1:8000/uploads/images/" + c.getImage();
            ImageViewer image_coach;
            Image imge;
            EncodedImage enc;
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge = URLImage.createToStorage(enc, url, url);
                 image_coach = new ImageViewer(imge);
        
        Label nom = new Label("Nom  : "+c.getNomSponsor());
        Label lieux = new Label("Societe  : "+c.getSociete());

                        
       
       m.setTextLine1(c.getNomSponsor());
        m.setTextLine2(c.getSociete());

     //  m.setTextLine2(c.getDateFinContrat());
          
        m.setEmblem(theme.getImage("round.png"));
      
            m.setIcon(imge);
              m.addActionListener(l
                -> {

            Form f2 = new Form("Detail",BoxLayout.y());
            
            f2.add(addItem_Cotch_detail(c));
             f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
                   new SponsorsForm(previous).showBack();
        });
            f2.show(); });
         
        return m;

    }
    
}
