/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.model;

import java.sql.Timestamp;

/**
 *
 * @author Ivica
 */
public class Narudzba
{
    private String brojNarudzbe;
    private Racun racun;

    public Narudzba() {
        brojNarudzbe = "";
        racun = null;
    }

    public Narudzba(String brojNarudzbe, Racun racun) {
        this.brojNarudzbe = brojNarudzbe;
        this.racun = racun;
    }

    public String getBrojNarudzbe() {
        return brojNarudzbe;
    }

    public void setBrojNarudzbe(String brojNarudzbe) {
        this.brojNarudzbe = brojNarudzbe;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }
    
    public Timestamp getDatumVrijeme()
    {
        return this.racun.getDatum_vrijeme();
    }
}
