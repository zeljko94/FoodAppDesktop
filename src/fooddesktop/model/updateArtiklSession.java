/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.model;

/**
 *
 * @author Nokia
 */
public class updateArtiklSession {
      public static ArtiklModel artikl;

    public static ArtiklModel getArtikl() {
        return artikl;
    }

    public static void setArtikl(ArtiklModel artikl) {
        updateArtiklSession.artikl= artikl;
    }
    
}
