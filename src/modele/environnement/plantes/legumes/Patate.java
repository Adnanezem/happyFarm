package modele.environnement.plantes.legumes;

import modele.environnement.plantes.Varietes;

public class Patate extends Legume 
{

    @Override
    public Varietes getVariete() 
    {
        return Varietes.PATATE;
    }

    @Override
    protected void croissance() 
    {
        niveau_croissance += 3;
    }

}