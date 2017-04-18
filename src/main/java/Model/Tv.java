/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Mark Kirby
 */
public class Tv {
    
    private boolean TvIsON;
    
    public Tv(boolean TvIsOn) {
        this.TvIsON = TvIsOn;
    }
    
    public boolean isTvON() {
        return TvIsON;
    }

    public void setTvIsON(boolean TvIsON) {
        this.TvIsON = TvIsON;
    }
}
