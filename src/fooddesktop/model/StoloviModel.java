/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import fooddesktop.model.Baza;


public class StoloviModel {
    SimpleIntegerProperty sifra = new SimpleIntegerProperty();
    SimpleIntegerProperty broj_stola = new SimpleIntegerProperty();
    


 
    
    public StoloviModel (Integer sifra, Integer broj_stola) {
        this.sifra = new SimpleIntegerProperty (sifra);
        this.broj_stola = new SimpleIntegerProperty(broj_stola);
        
    }
    
    public void azuriraj()
    {
        try {
            Baza DB = new Baza();
            PreparedStatement update = DB.exec("UPDATE stol SET broj_stola=? WHERE id=?");
            update.setInt(1, this.getBrojStola());
            update.setInt(2, this.getSifra());
            update.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoloviModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void brisi () {
        try {
            Baza DB = new Baza();
            PreparedStatement delete = DB.exec("DELETE FROM stol WHERE id=?");
            delete.setInt(1, this.sifra.getValue());
            delete.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoloviModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void spasi () {
        Baza DB = new Baza();
        PreparedStatement insert = DB.exec("INSERT INTO stol VALUES(null,?)");
        try {
            insert.setInt(1, this.broj_stola.getValue());
            
            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StoloviModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static StoloviModel dohvatiPrekoBrojStola(int broj_stola)
    {
        Baza DB = new Baza();
        StoloviModel stol = null;
        PreparedStatement dohvati = DB.exec("SELECT * FROM stol WHERE broj_stola=?");
        try {
            dohvati.setInt(1, broj_stola);
            ResultSet rs = dohvati.executeQuery();
            if(rs.next())
            {
               stol = new StoloviModel(0, rs.getInt("broj_stola"));
               stol.setSifra(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StoloviModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stol;
    }
    

    public Integer getSifra () {
        return sifra.get();
    }
    
    public void setSifra(int sifra)
    {
        this.sifra = new SimpleIntegerProperty(sifra);
    }
    
    public void setBrojStola(Integer brs)
    {
        this.broj_stola.set(brs);
    }
    public Integer getBrojStola () {
        return broj_stola.get();
    }
    
   
    
    

    
    
    public static ObservableList<StoloviModel> listaStolova () {
        ObservableList<StoloviModel> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM stol");
        
        try {
            while (rs.next()) {
                lista.add(new StoloviModel(rs.getInt("id"), rs.getInt("broj_stola")));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }
}