/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.model;

/**
 *
 * @author Ivica
 */
public class UpdateKorisnikHelper {
      public static KorisnikModel korisnik;

    public static KorisnikModel getKorisnik() {
        return korisnik;
    }

    public static void setKorisnik(KorisnikModel korisnik) {
        UpdateKorisnikHelper.korisnik = korisnik;
    }
}
