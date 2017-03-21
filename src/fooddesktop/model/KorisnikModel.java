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


public class KorisnikModel {
   
    SimpleIntegerProperty sifra = new SimpleIntegerProperty();
    SimpleStringProperty ime = new SimpleStringProperty();
    SimpleStringProperty prezime = new SimpleStringProperty();
    SimpleStringProperty broj_telefona = new SimpleStringProperty();
    SimpleStringProperty privilegije= new SimpleStringProperty();
    SimpleStringProperty korisnicko_ime = new SimpleStringProperty();
    SimpleStringProperty lozinka = new SimpleStringProperty();
      



 
    
    public KorisnikModel (Integer sifra, String ime, String prezime, String broj_telefona, String privilegije, String korisnicko_ime, String lozinka) {
        this.sifra = new SimpleIntegerProperty (sifra);
        this.ime = new SimpleStringProperty(ime);
        this.prezime = new SimpleStringProperty(prezime);
        this.broj_telefona = new SimpleStringProperty(broj_telefona);
        this.privilegije = new SimpleStringProperty( privilegije);
        this.korisnicko_ime = new SimpleStringProperty(korisnicko_ime);
        this.lozinka = new SimpleStringProperty(lozinka);
      
    }
    
    public void brisi () {
        try {
            Baza DB = new Baza();
            PreparedStatement delete = DB.exec("DELETE FROM korisnik  WHERE id=?");
            delete.setInt(1, this.sifra.getValue());
            delete.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KorisnikModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void spasi () {
        Baza DB = new Baza();
        PreparedStatement insert = DB.exec("INSERT INTO korisnik VALUES(null,?,?,?,?,?,?)");
        try {
            insert.setString(1, this.ime.getValue());
            insert.setString(2, this.prezime.getValue());
            insert.setString(3, this.broj_telefona.getValue());
            insert.setString(4, this.privilegije.getValue());
            insert.setString(5, this.korisnicko_ime.getValue());
            insert.setString(6, this.lozinka.getValue());
             

            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KorisnikModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void azuriraj () {
        Baza DB = new Baza();
        PreparedStatement update = DB.exec("UPDATE korisnik SET ime=?, prezime=?, username=?, password=?, broj_telefona=?, privilegije=? WHERE id=?");
        try {
            update.setString(1, this.ime.getValue());
            update.setString(2, this.prezime.getValue());
            update.setString(3, this.korisnicko_ime.getValue());
            update.setString(4, this.lozinka.getValue());
            update.setString(5, this.broj_telefona.getValue());
            update.setString(6, this.privilegije.getValue());
             update.setInt(7, this.sifra.getValue());

            update.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtiklModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void setSifra(Integer sifra){ this.sifra.set(sifra);}
    public Integer getSifra () {
        return sifra.get();
    }
    
        public static KorisnikModel logirajkorisnika(String korisnicko_ime, String lozinka){
    
        
        KorisnikModel rez = null;
        
        Baza db = new Baza();
        PreparedStatement ps = db.exec("SELECT * FROM korisnik WHERE username =? AND "
                + "password=?");
        try {
            ps.setString(1, korisnicko_ime);
            ps.setString(2, lozinka);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                rez=new KorisnikModel(rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("broj_telefona"),
                        rs.getString("privilegije"),
                        rs.getString("username"),
                        rs.getString("password"));
                        
                        
                rez.setSifra(rs.getInt("id"));
                
                
                
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška: "+ex.getMessage());
            return null;
        }
        return rez;
        
    }
    
    
   public static KorisnikModel dohvatiPrekoIda(int id)
    {
        KorisnikModel rez = null;
        
        Baza db = new Baza();
        PreparedStatement ps = db.exec("SELECT * FROM korisnik WHERE id=?");
        
        try {
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                rez=new KorisnikModel(rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("broj_telefona"),
                        rs.getString("privilegije"),
                        rs.getString("username"),
                        rs.getString("password"));
                        

                rez.setSifra(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KorisnikModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rez;
    }
   public static KorisnikModel dohvatiKimeLozinku(String username,String password)
    {
        KorisnikModel rez = null;
        
        Baza db = new Baza();
        PreparedStatement ps = db.exec("SELECT * FROM korisnik WHERE username=? AND password=?");
        
        try {
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                rez=new KorisnikModel(rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("broj_telefona"),
                        rs.getString("privilegije"),
                        rs.getString("username"),
                        rs.getString("password"));
                        

                rez.setKorisnicko_ime(rs.getString("username"));
                rez.setLozinka(rs.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KorisnikModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rez;
    }
    public static boolean logiraj (String kime, String lozinka) {
        Baza db = new Baza();
        PreparedStatement ps = db.exec("SELECT * FROM korisnik WHERE username =? AND "
                + "password=?");
        try {
            ps.setString(1, kime);
            ps.setString(2, lozinka);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška: "+ex.getMessage());
            return false;
        }
       
    }
    
    
  


    public String getIme() {
        return ime.get();
    }

    public void setIme(String ime) {
        this.ime = new SimpleStringProperty(ime);
    }

    public String getPrezime() {
        return prezime.get();
    }

    public void setPrezime(String prezime) {
        this.prezime = new SimpleStringProperty(prezime);
    }

    public  String getBroj_telefona() {
        return broj_telefona.get();
    }

    public void setBroj_telefona(String broj_telefona) {
        this.broj_telefona = new SimpleStringProperty (broj_telefona);
    }

    public String  getPrivilegije() {
        return privilegije.get();
    }

    public void setPrivilegije(String privilegije) {
        this.privilegije = new SimpleStringProperty (privilegije);
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime.get();
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = new SimpleStringProperty(korisnicko_ime);
    }

    public String getLozinka() {
        return lozinka.get();
    }

    public void setLozinka(String lozinka) {
        this.lozinka = new SimpleStringProperty(lozinka);
    }
   
     
     
    
    
    

    
    
    public static ObservableList<KorisnikModel> listaKorisnika () {
        ObservableList<KorisnikModel> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM korisnik");
        
        try {
            while (rs.next()) {
                lista.add(new KorisnikModel(rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("broj_telefona"),
                        rs.getString("privilegije"),
                        rs.getString("username"),
                        rs.getString("password")));
                        

            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }
}