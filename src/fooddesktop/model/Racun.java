/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivica
 */
public class Racun {
    private int id;
    private String sifra_racuna;
    private Timestamp datum_vrijeme;
    private double ukupan_iznos;
    private int id_konobara;
    private int id_stola;
    private boolean notifikacija;

    public Racun() {
        this.id = 0;
        this.sifra_racuna = "";
        this.datum_vrijeme = null;
        this.ukupan_iznos = 0;
        this.id_konobara = 0;
        this.id_stola = 0;
        this.notifikacija = true;
    }

    public Racun(String sifra_racuna, Timestamp datum_vrijeme, double ukupan_iznos, int id_konobara, int id_stola, boolean notifikacija) {
        this.sifra_racuna = sifra_racuna;
        this.datum_vrijeme = datum_vrijeme;
        this.ukupan_iznos = ukupan_iznos;
        this.id_konobara = id_konobara;
        this.id_stola = id_stola;
        this.notifikacija = notifikacija;
    }
    
    public static Racun resultSetToRacun(ResultSet resultSet)
    {
        Racun r = null;
        if(resultSet != null)
        {
            try {
                r = new Racun(resultSet.getString("sifra_racuna"),
                        resultSet.getTimestamp("datum_vrijeme"),
                        resultSet.getDouble("ukupan_iznos"),
                        resultSet.getInt("id_konobara"),
                        resultSet.getInt("id_stola"),
                        resultSet.getBoolean("notifikacija"));
                r.setId(resultSet.getInt("id"));
            } catch (SQLException ex) {
                Logger.getLogger(Racun.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return r;
    }
    
        public void azuriraj () {
        Baza DB = new Baza();
        PreparedStatement update = DB.exec("UPDATE racun SET sifra_racuna=?, datum_vrijeme=?, ukupan_iznos=?, id_konobara=?, id_stola=?, notifikacija=?, jeLiPotvrden=? WHERE id=?");
        try {
            update.setString(1, this.sifra_racuna);
            update.setTimestamp(2, this.datum_vrijeme);
            update.setDouble(3, this.ukupan_iznos);
            update.setInt(4, this.id_konobara);
            update.setInt(5, this.id_stola);
             update.setBoolean(6, this.notifikacija);
             update.setBoolean(7, false);
             update.setInt(8, this.id);

            update.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtiklModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<Racun> dohvatiNeprocitane()
    {
        ArrayList<Racun> rez = new ArrayList<>();
        Baza db = new Baza();
        ResultSet rs = db.select("SELECT * FROM racun WHERE notifikacija=0");
        try {
            while(rs.next())
            {
                Racun r = resultSetToRacun(rs);
                rez.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Racun.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rez;
    }
    
        public void brisi () {
        try {
            Baza DB = new Baza();
            PreparedStatement delete = DB.exec("DELETE FROM racun WHERE id=?");
            delete.setInt(1, this.id);
            delete.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtiklModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void print()
    {
        System.out.println("ID: " + this.id);
        System.out.println("Sifra racuna: " + this.sifra_racuna);
        System.out.println("Datum: " + this.datum_vrijeme);
        System.out.println("*********************");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSifra_racuna() {
        return sifra_racuna;
    }

    public void setSifra_racuna(String sifra_racuna) {
        this.sifra_racuna = sifra_racuna;
    }

    public Timestamp getDatum_vrijeme() {
        return datum_vrijeme;
    }

    public void setDatum_vrijeme(Timestamp datum_vrijeme) {
        this.datum_vrijeme = datum_vrijeme;
    }

    public double getUkupan_iznos() {
        return ukupan_iznos;
    }

    public void setUkupan_iznos(double ukupan_iznos) {
        this.ukupan_iznos = ukupan_iznos;
    }

    public int getId_konobara() {
        return id_konobara;
    }

    public void setId_konobara(int id_konobara) {
        this.id_konobara = id_konobara;
    }

    public int getId_stola() {
        return id_stola;
    }

    public void setId_stola(int id_stola) {
        this.id_stola = id_stola;
    }

    public boolean isNotifikacija() {
        return notifikacija;
    }

    public void setNotifikacija(boolean notifikacija) {
        this.notifikacija = notifikacija;
    }
}
