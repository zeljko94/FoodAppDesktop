/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.controller;

import fooddesktop.model.ArtiklModel;
import fooddesktop.model.Baza;
import fooddesktop.model.Narudzba;
import fooddesktop.model.OdabranaNarudzbaHelper;
import fooddesktop.model.Racun;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Ivica
 */

public class PregledNarucenogController implements Initializable{
    public static int brojac = 0;
    public static Image image = Toolkit.getDefaultToolkit().getImage("C:\\users\\korisnik\\desktop\\icon.png");
    public static TrayIcon trayIcon;
    public static SystemTray systemTray = SystemTray.getSystemTray();
    public static Baza db = new Baza();
    public static ObservableList<Racun> racuni = FXCollections.observableArrayList();
    public static ObservableList<Narudzba> narudzbeTblData = FXCollections.observableArrayList();
    
    @FXML
    TableView<Narudzba> narudzbeTbl;
    @FXML
    TableColumn<Narudzba,String> brojNarudzbeCol;
    @FXML
    TableColumn<Narudzba,Timestamp> datumNarudzbeCol;
    @FXML
    Button btnPregledNarudzbe;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        trayIcon = new TrayIcon(image);
        trayIcon.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Platform.runLater(new Runnable(){

                    @Override
                    public void run() {
                        LoginController.narudzbeStage.toFront();
                        for(int i=0; i<racuni.size(); i++)
                        {
                            Narudzba narudzba = new Narudzba("Narudzba " + (i+1), racuni.get(i));
                            narudzbeTblData.add(narudzba);
                        }
                        
                        brojNarudzbeCol.setCellValueFactory(new PropertyValueFactory<Narudzba, String>("BrojNarudzbe"));
                        datumNarudzbeCol.setCellValueFactory(new PropertyValueFactory<Narudzba, Timestamp>("DatumVrijeme"));
                        narudzbeTbl.setItems(narudzbeTblData);
                        
                        LoginController.narudzbeStage.show();
                    }
                });
            }
        });
        Timeline timeline;

        timeline=new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                        racuni = FXCollections.observableArrayList();
                        narudzbeTblData = FXCollections.observableArrayList();
                        brojac=0;
                        //trayIcon.displayMessage("Imate novu narudzbu ", "Broj novih narudžbi: "+Main.brojac++, TrayIcon.MessageType.INFO);
                         ResultSet podaci=db.select("SELECT * FROM racun WHERE notifikacija=0");

                            while(podaci.next()){
                                Racun r = Racun.resultSetToRacun(podaci);
                                racuni.add(r);
                                brojac++;
                            }
                            if(brojac>0){
                                if(brojac==1){
                                    trayIcon.setImage(image);
                                }
                                else if(brojac==2){
                                    trayIcon.setImage(image);  
                                }
                                else{
                                    trayIcon.setImage(image);
                                }
                                trayIcon.displayMessage("Imate novu narudžbu ", "Broj novih narudžbi: "+brojac, TrayIcon.MessageType.INFO);
                            }
                            else {
                                //trayIcon.setToolTip("Nema novih narudbi");
                                //trayIcon.displayMessage("Nemate novih narudžbi!!", "", TrayIcon.MessageType.INFO);  
                            }

                      } catch (SQLException ex) {

                      }
                      brojac=0;
                    }  
                }));
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
                
        try {
            systemTray.add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(PregledNarucenogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnPregledNarudzbe_onClick(ActionEvent e)
    {
        Narudzba odabrana = narudzbeTbl.getSelectionModel().getSelectedItem();
        
        OdabranaNarudzbaHelper.setNarudzba(odabrana);
        
        if(OdabranaNarudzbaHelper.getNarudzba() == null) return;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/PregledNarudzbe.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Pregled narudzbe");
            stage.setScene(new Scene(root, 1350, 700));
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ArtikliController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void otvoriLogin(ActionEvent e)
    {
        
        Parent root;
        try {
              root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Dobrodošli");
             stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
            
        } catch (Exception ex) {
            Logger.getLogger(ArtikliController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
