package modele.environnement;

import modele.Subscriber;

import modele.SimulateurPotager;
import modele.environnement.plantes.Plante;
import modele.environnement.plantes.Varietes;
import modele.item.Graines.Graine;

public class CaseCultivable extends Case implements Subscriber
{
    private Plante plante;
    protected int fertilite;
    
    public CaseCultivable()
    {
        super();
        fertilite = 5;
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
    public void planter(Graine p)
    {
        plante = p;
    }
    
}
