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
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;


public class ArtiklModel {
    
    SimpleIntegerProperty sifra = new SimpleIntegerProperty();
    SimpleStringProperty sifra_artikla = new SimpleStringProperty();
    SimpleStringProperty naziv_artikla = new SimpleStringProperty();
    SimpleDoubleProperty cijena = new SimpleDoubleProperty();
    SimpleIntegerProperty kolicina = new SimpleIntegerProperty();
    SimpleIntegerProperty id_kategorije = new SimpleIntegerProperty();
    //SimpleIntegerProperty id_vrste = new SimpleIntegerProperty();


    


 
    
    public ArtiklModel (Integer sifra, String sifra_artikla,String naziv_artikla, double cijena, Integer kolicina,Integer id_kategorije /*Integer id_vrste*/) {
        this.sifra = new SimpleIntegerProperty (sifra);
        this.sifra_artikla = new SimpleStringProperty(sifra_artikla);
        this.naziv_artikla = new SimpleStringProperty(naziv_artikla);
        this.cijena = new SimpleDoubleProperty(cijena);
        this.kolicina = new SimpleIntegerProperty(kolicina);
        this.id_kategorije = new SimpleIntegerProperty(id_kategorije);
        //this.id_vrste = new SimpleIntegerProperty(id_vrste);

        
    }
    
    public void brisi () {
        try {
            Baza DB = new Baza();
            PreparedStatement delete = DB.exec("DELETE FROM artikl WHERE id=?");
            delete.setInt(1, this.sifra.getValue());
            delete.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtiklModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void azuriraj () {
        Baza DB = new Baza();
        PreparedStatement update = DB.exec("UPDATE artikl SET sifra_artikla=?, naziv_artikla=?, cijena=?, kolicina=?, id_kategorije=? WHERE id=?");
        try {
            update.setString(1, this.sifra_artikla.getValue());
            update.setString(2, this.naziv_artikla.getValue());
            update.setDouble(3, this.cijena.getValue());
            update.setInt(4, this.kolicina.getValue());
            update.setInt(5, this.id_kategorije.getValue());
            //update.setInt(6, this.id_vrste.getValue());
             update.setInt(6, this.sifra.getValue());

            update.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtiklModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void spasi () {
        Baza DB = new Baza();
        PreparedStatement insert = DB.exec("INSERT INTO artikl(sifra_artikla,naziv_artikla,cijena,kolicina,id_kategorije) VALUES(?,?,?,?,?)");
        try {
            insert.setString(1, this.sifra_artikla.getValue());
            insert.setString(2, this.naziv_artikla.getValue());
            insert.setDouble(3, this.cijena.getValue());
            insert.setInt(4, this.kolicina.getValue());
            insert.setInt(5, this.id_kategorije.getValue());
            //insert.setInt(6, this.id_vrste.getValue());
            

            
            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtiklModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArtiklModel dohvatiPrekoSifraArtikla(String sifra_artikla)
    {
        Baza DB = new Baza();
        ArtiklModel artikl= null;
        PreparedStatement dohvati = DB.exec("SELECT * FROM artikl WHERE sifra_artikla=?");
        try {
            dohvati.setString(1, sifra_artikla);
            ResultSet rs = dohvati.executeQuery();
            if(rs.next())
            {
               artikl = new ArtiklModel(0, rs.getString("sifra_artikla"),rs.getString("naziv_artikla"),rs.getDouble("cijena"),rs.getInt("kolicina"),rs.getInt("id_kategorije")/*,rs.getInt("id_vrste")*/);
               artikl.setSifra(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtiklModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artikl;
    }
    

    public Integer getSifra () {
        return sifra.get();
    }
    
    public void setSifra(int sifra)
    {
        this.sifra = new SimpleIntegerProperty(sifra);
    }
    
    public String getSifraArtikla () {
        return sifra_artikla.get();
    }
    public void setSifraArtikla(String sifra_artikla)
    {
        this.sifra_artikla = new SimpleStringProperty(sifra_artikla);
    }
    
    public String getNazivArtikla () {
        return naziv_artikla.get();
    }
    public void setNazivArtikla(String naziv_artikla)
    {
        this.naziv_artikla = new SimpleStringProperty(naziv_artikla);
    }
    
     public Double getCijena () {
        return cijena.get();
    }
    public void setCijena(Double cijena)
    {
        this.cijena = new SimpleDoubleProperty(cijena);
    }
     public Integer getKolicina () {
        return kolicina.get();
    }
    public void setKolicina(Integer kolicina)
    {
        this.kolicina = new SimpleIntegerProperty(kolicina);
    }
     public Integer getIdKategorije () {
        return id_kategorije.get();
    }
    public void setIdKategorije(Integer id_kategorije)
    {
        this.id_kategorije = new SimpleIntegerProperty(id_kategorije);
    }
     /*public Integer getIdVrste() {
        return id_vrste.get();
    }
    public void setIdVrste(Integer id_vrste)
    {
        this.id_vrste = new SimpleIntegerProperty(id_vrste);
    }*/
   
  
    
    public String getKategorijaToString()
    {
        Kategorija k = Kategorija.dohvatiPrekoId(this.id_kategorije.get());
        return k.toString();
    }
    
    /*public String getVrstaToString()
    {
        Vrsta v = Vrsta.dohvatiPrekoId(this.id_vrste.get());
        return v.toString();
    }*/
    
    public static ArtiklModel resultSetToArtiklModel(ResultSet rs)
    {
        ArtiklModel artikl = null;
        try {
            artikl = new ArtiklModel(0, rs.getString("sifra_artikla"),rs.getString("naziv_artikla"),rs.getDouble("cijena"),rs.getInt("kolicina"),rs.getInt("id_kategorije"));
        } catch (SQLException ex) {
            Logger.getLogger(ArtiklModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artikl;
    }
    
    public static ArtiklModel dohvatiPrekoId(int id)
    {
        ArtiklModel artikl = null;
        Baza DB = new Baza();
        PreparedStatement ps = DB.exec("SELECT * FROM artikl WHERE id=?");
        
        try {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
                artikl = ArtiklModel.resultSetToArtiklModel(rs);
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return artikl;
    }
    
    public static ObservableList<ArtiklModel> listaArtikala() {
        ObservableList<ArtiklModel> lista = FXCollections.observableArrayList();
        Baza DB = new Baza();
        ResultSet rs = DB.select("SELECT * FROM artikl");
        
        try {
            while (rs.next()) {
                lista.add(new ArtiklModel(rs.getInt("id"), rs.getString("sifra_artikla"),rs.getString("naziv_artikla"),rs.getFloat("cijena"),rs.getInt("kolicina"),rs.getInt("id_kategorije")/*,rs.getInt("id_vrste")*/));
            }
        } catch (SQLException ex) {
            System.out.println("Nastala je greška prilikom iteriranja: " + ex.getMessage());
        }
        return lista;
    }
}