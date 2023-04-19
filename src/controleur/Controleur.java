package controleur;

import javax.swing.UnsupportedLookAndFeelException;

import modele.Simulation;
import vue.Vue;

public class Controleur 
{

    public static void main(String[] args) throws UnsupportedLookAndFeelException, CloneNotSupportedException
    {
        Simulation simul = new Simulation();
        Vue view = new Vue(simul);  
        view.setVisible(true);
        
    }
}
