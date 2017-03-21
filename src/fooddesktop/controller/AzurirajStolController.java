/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.controller;

import fooddesktop.model.StoloviModel;
import fooddesktop.model.UpdateStolHelper;
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

/**
 *
 * @author Ivica
 */
public class AzurirajStolController implements  Initializable{

    @FXML
    TextField brojStolaTxt;
    
    @FXML
    Button btnDodaj;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        brojStolaTxt.setText(UpdateStolHelper.getStol().getBrojStola().toString());
    }
    
    public void btnDodaj_onClick(ActionEvent e)
    {
        StoloviModel stol = UpdateStolHelper.getStol();
        stol.setBrojStola(Integer.parseInt(brojStolaTxt.getText()));
        stol.azuriraj();
  
     {
        try {
            
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Stolovi.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Prikaz svih stolova u bazi podataka");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AzurirajStolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    }
      public void otvoriStolove(ActionEvent e)
    {
        try {
            
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Stolovi.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Prikaz svih stolova u bazi podataka");
            stage.setScene(new Scene(root));
            stage.setResizable(true);
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AzurirajStolController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
