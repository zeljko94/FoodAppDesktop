/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author Ivica
 */
public class Kategorija {
    private int id;
    private String naziv;

    public Kategorija() {
        id = 0;
        naziv = "";
    }

    public Kategorija(String naziv) {
        this.id = 0;
        this.naziv = naziv;
    }
    
    
    public static ObservableList<Kategorija> dohvatiSve()
    {
        ObservableList<Kategorija> rez = FXCollections.observableArrayList();
        Baza db =  new Baza();
        ResultSet rs = db.select("SELECT * FROM kategorije");
        
        try {
            while(rs.next())
            {
                Kategorija kategorija = new Kategorija(rs.getString("naziv"));
                kategorija.setId(rs.getInt("id"));
                rez.add(kategorija);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kategorija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rez;
    }
    
    public static Kategorija dohvatiPrekoId(int id)
    {
        Kategorija k = null;
        Baza db = new Baza();
        PreparedStatement ps = db.exec("SELECT * FROM kategorije WHERE id=?");
        
        try {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                k = new Kategorija(rs.getString("naziv"));
                k.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kategorija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
    }

    @Override
    public String toString()
    {
        return this.naziv;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
    
}
