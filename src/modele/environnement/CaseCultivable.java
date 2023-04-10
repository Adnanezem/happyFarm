package modele.environnement;

import modele.SimulateurPotager;
import modele.environnement.varietes.Plante;
import modele.environnement.varietes.Varietes;

public class CaseCultivable extends Case
{
    private Plante plante;
    
    public CaseCultivable(int _humidite)
    {
        super(_humidite);
    }
    public Plante get_plante() 
    {
        return plante;
    }



    public void update()
    {
        if(plante != null)
        {
            plante.nextStep();
        }
    }

    
}
