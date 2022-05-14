/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Tournois;
import com.mycompany.service.TournoisService;
import java.util.List;
import models.Session;

/**
 *
 * @author USER
 */
public class JeuForm extends Form {
   
    public JeuForm() {
        Resources resourceObjectInstance = null;
        initGuiBuilderComponents(resourceObjectInstance);

//        sl = new ScaleImageLabel(resourceObjectInstance.getImage("bridge.jpg"));
//        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//        gui_imageContainer11.add(BorderLayout.CENTER, sl);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });

//        FontImage.setMaterialIcon(gui_newYork, FontImage.MATERIAL_LOCATION_ON);
//        gui_newYork.setIconPosition(BorderLayout.EAST);
//        gui_Text_Area_1.setRows(2);
//        gui_Text_Area_1.setColumns(100);
//        gui_Text_Area_1.setGrowByContent(false);
//        gui_Text_Area_1.setEditable(false);
Resources res = null;
Form previous = null;
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

//-- DON'T EDIT BELOW THIS LINE!!!
    //private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_newYork = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        TournoisService comp = new TournoisService();
        List<Tournois> ls = comp.getJeus();

        for (Tournois course : ls) {
            setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
            setTitle("Tournoi");
            setName("JeuxForm");
            com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.components.MultiButton gui_LA = new com.codename1.components.MultiButton();

            addComponent(gui_Container_1);
            gui_Container_1.setName("Container_1");
            com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);
            gui_Multi_Button_1.setPropertyValue("line1", course.getNom());
            gui_Multi_Button_1.setPropertyValue("line2", course.getDateDeb());
            gui_Multi_Button_1.setPropertyValue("line2", course.getDateFin());
            gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
            gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");

            gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
            gui_LA.setPropertyValue("uiid2", "RedLabelRight");

            gui_LA.setIconPosition(BorderLayout.EAST);
            com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
            com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
            com.codename1.ui.Button update = new com.codename1.ui.Button();
            com.codename1.ui.Button add = new com.codename1.ui.Button();
            com.codename1.ui.Label gui_separator1 = new com.codename1.ui.Label();
            addComponent(gui_imageContainer1);
            gui_imageContainer1.setName("imageContainer1");
            gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, update);
            gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);  
            gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, add);  
            add.setText("add");
            add.setUIID("Label");
            add.setName("Button_3");

            gui_Button_1.setText("delete");
            gui_Button_1.setUIID("Label");
            gui_Button_1.setName("Button_1");
            update.setText("update");
            update.setUIID("Label");
            update.setName("Button_2");
            gui_Text_Area_1.setRows(2);
            gui_Text_Area_1.setColumns(100);
            gui_Text_Area_1.setGrowByContent(false);
            gui_Text_Area_1.setEditable(false);
            com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();

            addComponent(gui_Label_1_1_1);
            gui_Label_1_1_1.setShowEvenIfBlank(true);
            gui_Container_1.setName("Container_1");
            gui_imageContainer1.setName("imageContainer1");
            gui_separator1.setUIID("Separator");
            gui_separator1.setName("separator1");
            gui_separator1.setShowEvenIfBlank(true);
            gui_Button_1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    TournoisService serv = new TournoisService();
                    serv.desc(course);
                    new JeuForm().show();
                }
            });
            
//             update.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    TournoisService serv = new TournoisService();
//                    Jeu e = new Jeu();
//                    e = serv.fetch(course.getNom());
//                    new UpdateJeuForm(resourceObjectInstance, e).show();
//                }
//            });
             
             add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new AddJeuForm(resourceObjectInstance).show();
                }
            });
//              gui_Multi_Button_1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                new HomeForm(resourceObjectInstance).show();
//            }
//        });
        }
    }
}