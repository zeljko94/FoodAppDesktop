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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ivica
 */
public class Vrsta {
    private int id;
    private String naziv;

    public Vrsta() {
        this.id = 0;
        this.naziv = "";
    }

    public Vrsta(String naziv) {
        this.id = 0;
        this.naziv = naziv;
    }
    
    public static ObservableList<Vrsta> dohvatiSve()
    {
        ObservableList<Vrsta> rez = FXCollections.observableArrayList();
        Baza db =  new Baza();
        ResultSet rs = db.select("SELECT * FROM vrsta");
        
        try {
            while(rs.next())
            {
                Vrsta vrsta = new Vrsta(rs.getString("naziv"));
                vrsta.setId(rs.getInt("id"));
                rez.add(vrsta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kategorija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rez;
    }
    
    public static Vrsta dohvatiPrekoId(int id)
    {
        Vrsta v = null;
        Baza db = new Baza();
        PreparedStatement ps = db.exec("SELECT * FROM vrsta WHERE id=?");
        
        try {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                v = new Vrsta(rs.getString("naziv"));
                v.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kategorija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
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
