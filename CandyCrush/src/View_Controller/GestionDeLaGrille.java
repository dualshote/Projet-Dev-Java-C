/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Case;
import Model.Grille;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author dualshote
 */
public class GestionDeLaGrille implements MouseListener{
    
    private Grille maGrille;
    private CaseGrille caseSelected;
    private int point;
    private int minute,seconde;
    private int departChrono = 0;
    
    public GestionDeLaGrille(Grille maGrille, int minute, int seconde){
        this.maGrille = maGrille;
        this.caseSelected = null;
        this.minute = minute;
        this.seconde = seconde;
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        CaseGrille maCaseGrille = (CaseGrille) e.getSource();
        GestionChrono gestionChrono = new GestionChrono(this.minute, this.seconde);
        if(gestionChrono.getChrono().getPartieTerminee() == false){ //Permet de savoir si la partie est terminée
            if(departChrono == 0){
                GestionChrono.setDebutChrono(1);
                departChrono = 1;
            }
                if(caseSelected == null){
                    caseSelected = maCaseGrille;
                    maCaseGrille.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
                    if(GestionChrono.getDebutChrono() == 1){ //On lance le chrono si l'utilisateur fait un clique
                        gestionChrono.start();
                        GestionChrono.setDebutChrono(0);
                    }
                }
                else if(maCaseGrille != this.caseSelected && 
                        ((maCaseGrille.x == caseSelected.x + 1 || maCaseGrille.x == caseSelected.x - 1) && maCaseGrille.y == caseSelected.y) ||
                        ((maCaseGrille.y == caseSelected.y + 1 || maCaseGrille.y == caseSelected.y - 1) && maCaseGrille.x == caseSelected.x)){

                    Case maCase1 = maGrille.getCase(caseSelected.x, caseSelected.y);
                    Case maCase2 = maGrille.getCase(maCaseGrille.x, maCaseGrille.y);
                    
                    GestionAgregation gestionAgregation = new GestionAgregation(maCase1, false); //False pour dire que ce n'est pas l'initialisation
                    gestionAgregation.testCase(maCase2);    //Intervertit pas les cases si l'agregation n'est pas possible

                    this.caseSelected.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    caseSelected = null;
                }
                else{
                    this.caseSelected.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    caseSelected = null;
                }
        }
        else{
            JOptionPane.showMessageDialog(null,"La partie est terminée, il faut recommencer une nouvelle partie !");
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {    //Permet de colorier les bordures au passage de la souris
        CaseGrille maCaseGrille = (CaseGrille) e.getSource();
        if(maCaseGrille != caseSelected){
            maCaseGrille.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) { //Permet de remettre la couleur de base quand la souris quitte la case
        if(e.getSource() != this.caseSelected){
            CaseGrille maCaseGrille = (CaseGrille) e.getSource();
            maCaseGrille.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        }
    }
}
