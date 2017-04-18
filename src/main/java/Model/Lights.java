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
public class Lights {
    
    private boolean LightIsOn;
    
    public Lights(boolean LightIsOn) {
        this.LightIsOn = LightIsOn;
    }
    
    public boolean isAudioON() {
        return LightIsOn;
    }

    public void setLightIsON(boolean AudioIsON) {
        this.LightIsOn = LightIsOn;
    }
    
}
