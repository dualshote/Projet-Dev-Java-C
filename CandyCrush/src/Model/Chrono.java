/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;

/**
 *
 * @author benoit
 */
public class Chrono extends Observable implements ActionListener, Serializable{
    
    private int timeMinute;
    private int timeSeconde;
    private boolean partieTermine;

    public Chrono(int initMinute, int initSeconde) {
          super();
          this.timeMinute = initMinute;
          this.timeSeconde = initSeconde;
    }
    
    public boolean getPartieTerminee(){
        return this.partieTermine;
    }
    
    public void setPartieTerminee(){
        this.partieTermine = true;
    }

    public int getTimeMinute() {
        return timeMinute;
    }

    public int getTimeSeconde() {
        return timeSeconde;
    }
    
    public void setChronoNull(){
        this.timeMinute = 0;
        this.timeSeconde = 0;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {    //Decompte le chrono
        if (this.timeSeconde != -1 || this.timeMinute != 0){    //-1 pour afficher le 0
            if (this.timeSeconde == 0) {
                this.timeMinute--;
                this.timeSeconde = 59;
            }
            else{
                this.timeSeconde--;
            }
            if(this.partieTermine == false){
                System.out.println("Temps restant : " + this.timeMinute + " : " + this.timeSeconde);
            }
            if(this.timeSeconde == 0 && this.timeMinute == 0){
                System.out.println("Temps écoulé, partie terminée !!"); //Met fin à la partie
                this.partieTermine = true;
            }
            setChanged();
            notifyObservers();
        }
    }

    public void setChrono(int minutes, int secondes){
        this.timeMinute = minutes;
        this.timeSeconde = secondes;
        setChanged();
        notifyObservers();
    }
    
    
    @Override
    public String toString(){   //Permet d'afficher le chrono
        return "Temps : " + this.timeMinute + " : " + this.timeSeconde;
    }
    
}
