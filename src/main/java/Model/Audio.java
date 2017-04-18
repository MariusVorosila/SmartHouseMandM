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
public class Audio {
    
    private boolean AudioIsON;
    
    public Audio(boolean AudioIsOn) {
        this.AudioIsON = AudioIsON;
    }
    
    public boolean isAudioON() {
        return AudioIsON;
    }

    public void setAudioIsON(boolean AudioIsON) {
        this.AudioIsON = AudioIsON;
    }
    
}
