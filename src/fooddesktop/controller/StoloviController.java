/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.controller;

import java.io.IOException;

import fooddesktop.model.StoloviModel;
import fooddesktop.model.UpdateStolHelper;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class StoloviController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableColumn<StoloviModel, Integer> idCol;
    @FXML
    TableColumn<StoloviModel, Integer> brojStolaCol;
     @FXML
    TableView stoloviTbl;
    
     @FXML
     Button btnUrediStol;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<StoloviModel> data = StoloviModel.listaStolova();
        
        brojStolaCol.setCellValueFactory(new PropertyValueFactory<StoloviModel, Integer>("BrojStola"));
        idCol.setCellValueFactory(new PropertyValueFactory<StoloviModel, Integer>("Sifra"));
        
        stoloviTbl.setItems(data);
    }   
    
      public void otvoriDodajStol (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/DodajStol.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Dodaj stol");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(StoloviController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 
       public void otvoriAdmin (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/AdminPocetna.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Administrativne radnje");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(StoloviController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
      
       public void brisiStol () {
        StoloviModel stol = (StoloviModel) this.stoloviTbl.getSelectionModel().getSelectedItem();
        stol.brisi();
        this.initialize(null, null);
    }
       
       public void btnUrediStol_onClick(ActionEvent e)
       {
        Parent root;
        try {
            StoloviModel odabrani = (StoloviModel) this.stoloviTbl.getSelectionModel().getSelectedItem();
            if(odabrani == null) return;
            UpdateStolHelper.setStol(odabrani);
            
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/AzurirajStol.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Azuriranje stola");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(StoloviController.class.getName()).log(Level.SEVERE, null, ex);
        } 
       }
    
}
