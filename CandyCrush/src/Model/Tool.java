/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Random;

/**
 *
 * @author Neo
 */
public class Tool {
 
    public static int monRandom(int min, int max){
        //fonction pour générer un nombre aleatoire (utilise pour generer la forme)
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
}
