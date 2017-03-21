/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fooddesktop.model.StoloviModel;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class DodajStolController implements Initializable {
    @FXML
    TextField brojStolaTxt;
    @FXML
    Label errorMsgLbl;
    
    
    /**
     * Initializes the controller class.
     */
    
    public void dodajStol (ActionEvent e) throws IOException {
        if(brojStolaTxt!=null)
        {
            errorMsgLbl.setTextFill(Paint.valueOf("RED"));
            errorMsgLbl.setText("Niste unijeli broj stola!");
        }
      
        try {
            StoloviModel stol = new StoloviModel (0,Integer.parseInt(this.brojStolaTxt.getText()));
            
            if(StoloviModel.dohvatiPrekoBrojStola(stol.getBrojStola()) == null)
            {
                stol.spasi();
                  Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Stolovi.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Stolovi");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
            }
          
            
            
            else {
               errorMsgLbl.setTextFill(Paint.valueOf("RED"));
                errorMsgLbl.setText("Unijeli ste postojeći stol!");
            }
          
        } catch (IOException ex) {
            Logger.getLogger(DodajStolController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void otvoriStolove (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Stolovi.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Stolovi");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(DodajStolController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
    
}
