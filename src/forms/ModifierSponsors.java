/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import models.Sponsors;
import services.Service_Sponsors;

/**
 *
 * @author Asus
 */
public class ModifierSponsors extends BaseForm{
     Form current;

    public ModifierSponsors(Resources res, Sponsors rec) {
       super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Modifier Sponsors");
        getContentPane().setScrollVisible(false);
        
        
//////////////////////////////////////////////////////////////////

tb.addSearchCommand(e -> {

        });
        Tabs swipe = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();
        addTab(swipe, s1, res.getImage("back-logoo.jpg"), "", "", res);

        /////////////////////////////////////////////////
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));



        
        
        
        //TextField Sujetrec = new TextField(rec.getSujetRec(), "Sujet", 20, TextField.ANY);
        
        TextField nom = new TextField(rec.getNomSponsor(), "nom", 20, TextField.ANY);
        
        
               TextField societe = new TextField(rec.getSociete(), "societe", 20, TextField.ANY);

       
        TextField montant = new TextField(String.valueOf(rec.getMontant()), "montant", 20, TextField.ANY);
        TextField duree = new TextField(String.valueOf(rec.getDureeSpons()), "duree", 20, TextField.ANY);

        
 
        nom.setUIID("NewsTopLine");
        nom.setSingleLineTextArea(true);

        societe.setUIID("NewsTopLine");
        societe.setSingleLineTextArea(true);
            
        montant.setUIID("NewsTopLine");
        montant.setSingleLineTextArea(true);
        
          duree.setUIID("NewsTopLine");
        duree.setSingleLineTextArea(true);
        
        
        
        Button btnModifier = new Button("Modifier");

      
        //Evenet onClick btnModifier
        btnModifier.addPointerPressedListener(l -> {
          rec.setNomSponsor(nom.getText());
                    rec.setSociete(societe.getText());

           rec.setMontant(Integer.valueOf(montant.getText()));
           
          rec.setDureeSpons(Integer.valueOf(duree.getText()));


            if (Service_Sponsors.getInstance().ModifierSponsor(rec)) {
                new ListeSponsors(res).show();
            }
            
        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(l -> {
            new ListeSponsors(res).show();
        });

        Label l1 = new Label();
        Label l2 = new Label();
        Label l3 = new Label();
        Label l4 = new Label();
        Label l5 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(nom),
                createLineSeparator(),
                 new FloatingHint(societe),
                createLineSeparator(),
                 new FloatingHint(montant),
                createLineSeparator(),
                    new FloatingHint(duree),
                createLineSeparator(),
              
                btnModifier,
                btnAnnuler
        );
        add(content);
        show();

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

        Container page1 = LayeredLayout.encloseIn(
                imageScale,
                overLay,
                BorderLayout.south(
                        BoxLayout.encloseY(
                                new SpanLabel(text, "LargeWhiteText"),
                                spacer
                        )
                )
        );

        swipe.addTab("", res.getImage("back-logo.jpeg"), page1);
    }
        
            private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2); //b.getx()+ btn.getWidh()/ 2 - l.getWidth() / +
        l.getParent().repaint();
    }
            
                public void bindButtonSelection(Button btn, Label l) {
        btn.addActionListener(e -> {
            if (btn.isSelected()) {
                updateArrowPosition(btn, l);
            }
        });
        
        

    }
}
