/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import com.codename1.components.MultiButton;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

import com.mycompany.entities.Tournois;
import com.mycompany.service.TournoisService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class AddJeuForm extends Form {

    private Container gui_Container_1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    private Label gui_Label_1 = new Label();
    private ComponentGroup gui_Component_Group_1 = new ComponentGroup();
    private TextField name = new TextField("", "Name", 20, TextField.ANY);
        private TextField jeux = new TextField("", "Name", 20, TextField.ANY);

    private Button signupbutt = new Button();
    private TextField description = new TextField("", "enter description");
    

    public AddJeuForm() {
        this(Resources.getGlobalResources());
    }

    public AddJeuForm(Resources resourceObjectInstance) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");

        
        signupbutt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (isFormEmpty()) {
                    Tournois u = new Tournois();
                    u.setNom(name.getText());
                    u.setJeux(jeux.getText());
                    
                   
                
                    TournoisService us = new TournoisService();
                    boolean cre = us.Create(u);
                    if (cre) {
                        Dialog.show("Creation Successful", "Welcome to ", "ok", null);
                        new JeuForm().show();

                    } else {
                        Dialog.show("Cannot Process request", "You must fill all fields before submitting for creation", "ok", null);
                    }
                }

            }
        }
        );

    }

    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        signupbutt.addActionListener(callback);
    }

    class EventCallbackClass implements ActionListener, DataChangedListener {

        private Component cmp;

        public EventCallbackClass(Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(ActionEvent ev) {
            Component sourceComponent = ev.getComponent();
            if (sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if (sourceComponent == signupbutt) {
                //onButton_2ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }


    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new BorderLayout());
        setTitle("Sign In");
        setName("SignInForm");
        addComponent(BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(name);
        gui_Component_Group_1.addComponent(jeux);
//        gui_Component_Group_1.addComponent(datePicker);
//        gui_Component_Group_1.addComponent(cat);

//         CategorieService comp = new CategorieService();
//                    List<CategorieC> ls = comp.getCategories();
//                    
//                    for (CategorieC us : ls) {
//                        cat.addItem(us.getNom());
//                    }
        //gui_Component_Group_1.addComponent(jeu);
        
        description.setUIID("TextFieldBlack");
        
        name.setName("nomejeu");
        gui_Container_1.addComponent(signupbutt);
        gui_Component_Group_1.setName("Component_Group_1");
        signupbutt.setText("Create");
        signupbutt.setName("Button_2");
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
    }// </editor-fold>

    private boolean isFormEmpty() {
        return !name.getText().equals("") ;
    }

}
