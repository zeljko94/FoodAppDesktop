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
public class RacunStavka {
    private int id;
    private int kolicina;
    private int id_artikla;
    private int id_racuna;
    private double cijena_stavke;

    public RacunStavka() {
        id = 0;
        kolicina = 0;
        id_artikla = 0;
        id_racuna = 0;
        cijena_stavke = 0;
    }

    public RacunStavka(int kolicina, int id_artikla, int id_racuna, double cijena_stavke) {
        this.id = 0;
        this.kolicina = kolicina;
        this.id_artikla = id_artikla;
        this.id_racuna = id_racuna;
        this.cijena_stavke = cijena_stavke;
    }
    
    public static RacunStavka resultSetToRacunStavka(ResultSet rs)
    {
        RacunStavka stavka = null;
        try {
            stavka = new RacunStavka(rs.getInt("kolicina"),
                    rs.getInt("id_artikla"),
                    rs.getInt("id_racuna"),
                    rs.getDouble("cijena_stavke"));
            stavka.setId(rs.getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(RacunStavka.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stavka;
    }
    
    public static ObservableList<RacunStavka> dohvatiStavkeSaRacuna(int id)
    {
        ObservableList stavke = FXCollections.observableArrayList();
        Baza DB = new Baza();
        PreparedStatement ps = DB.exec("SELECT * FROM racun_stavke WHERE id_racuna=?");
        
        try {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stavke.add(RacunStavka.resultSetToRacunStavka(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return stavke;
    }
    
    public void brisi () {
        try {
            Baza DB = new Baza();
            PreparedStatement delete = DB.exec("DELETE FROM racun_stavke WHERE id=?");
            delete.setInt(1, this.id);
            delete.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtiklModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getId_artikla() {
        return id_artikla;
    }

    public void setId_artikla(int id_artikla) {
        this.id_artikla = id_artikla;
    }

    public int getId_racuna() {
        return id_racuna;
    }

    public void setId_racuna(int id_racuna) {
        this.id_racuna = id_racuna;
    }

    public double getCijenaStavke() {
        return cijena_stavke;
    }

    public void setCijenaStavke(double cijena_stavke) {
        this.cijena_stavke = cijena_stavke;
    }
    
    public String getNazivArtikla()
    {
        return ArtiklModel.dohvatiPrekoId(this.id_artikla).getNazivArtikla();
    }
    
    
    
}
