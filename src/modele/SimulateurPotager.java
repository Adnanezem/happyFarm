/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import java.awt.Point;

import modele.environnement.Case;
import modele.environnement.CaseCultivable;
import modele.environnement.CaseNonCultivable;
import modele.environnement.plantes.Plante;
import modele.item.graines.Graine;
import modele.meteo.SimulateurMeteo;


public class SimulateurPotager 
{

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 10;

    // private HashMap<Case, Point> map = new  HashMap<Case, Point>(); // permet de récupérer la position d'une entité à partir de sa référence
    private Case[][] grilleCases = new Case[SIZE_X][SIZE_Y]; // permet de récupérer une entité à partir de ses coordonnées

    public SimulateurPotager(Minuteur minuteur, SimulateurMeteo _meteo) 
    {
        init_tiles(minuteur , _meteo);
    }


    
    public Case[][] getPlateau() 
    {
        return grilleCases;
    }
    
    private void init_tiles(Minuteur minuteur, SimulateurMeteo _meteo) 
    {

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

        for (int x = 5; x < 15; x++) 
        {
            for (int y = 3; y < 7; y++) 
            {
                CaseCultivable cc = new CaseCultivable(_meteo);
                addEntite(cc , x, y);
                minuteur.add_subscriber(cc);

            }
        }

    }

    

    private void addEntite(Case e, int x, int y) 
    {
        grilleCases[x][y] = e;
    }


    private Case objetALaPosition(Point p) 
    {
        return grilleCases[p.x][p.y];
    }

    public Plante recolter_plante(int x, int y)
    {
        if(grilleCases[y][x] instanceof CaseNonCultivable) return null;
        CaseCultivable temp = (CaseCultivable) grilleCases[y][x];
        return temp.recolter_plante();
    }

    public boolean planter(int x, int y, Graine g)
    {
        if(grilleCases[y][x] instanceof CaseNonCultivable) return false;
        CaseCultivable temp = (CaseCultivable) grilleCases[y][x];
        temp.set_plante(g);
        return true;
    }

}
