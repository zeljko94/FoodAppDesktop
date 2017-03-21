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
public class Sesija {
    static KorisnikModel konobar;
    
    public static KorisnikModel getKonobar(){ return konobar; }
    
    public static void setKorisnik(KorisnikModel k){ konobar = k; }
}
