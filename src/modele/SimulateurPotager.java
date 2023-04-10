/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import modele.environnement.Case;
import modele.environnement.CaseCultivable;
import modele.environnement.CaseNonCultivable;

import java.awt.Point;


public class SimulateurPotager {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 10;

    private SimulateurMeteo simMet;
    private Minuteur minuteur;

    // private HashMap<Case, Point> map = new  HashMap<Case, Point>(); // permet de récupérer la position d'une entité à partir de sa référence
    private Case[][] grilleCases = new Case[SIZE_X][SIZE_Y]; // permet de récupérer une entité à partir de ses coordonnées

    public SimulateurPotager() 
    {

        simMet = new SimulateurMeteo();
        initialisationDesEntites();
        minuteur = new Minuteur();

    }


    
    public Case[][] getPlateau() {
        return grilleCases;
    }
    
    private void initialisationDesEntites() {

        // murs extérieurs horizontaux
        for (int x = 0; x < 20; x++) {
            addEntite(new CaseNonCultivable(), x, 0);
            addEntite(new CaseNonCultivable(), x, 9);
        }

        // murs extérieurs verticaux
        for (int y = 0; y < 9; y++) {
            addEntite(new CaseNonCultivable(), 0, y);
            addEntite(new CaseNonCultivable(), 19, y);
        }

        addEntite(new CaseNonCultivable(), 2, 6);
        addEntite(new CaseNonCultivable(), 3, 6);

        for (int x = 5; x < 15; x++) {
            for (int y = 3; y < 7; y++) {
                CaseCultivable cc = new CaseCultivable(simMet.get_humidite());
                addEntite(cc , x, y);
                minuteur.add_subscriber(cc);

            }
        }

    }

    

    private void addEntite(Case e, int x, int y) 
    {
        grilleCases[x][y] = e;
        //map.put(e, new Point(x, y));
    }


    private Case objetALaPosition(Point p) 
    {
        return grilleCases[p.x][p.y];
    }

}
