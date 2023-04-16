package modele.environnement.plantes.legumes;

import modele.environnement.plantes.Varietes;

public class Salade extends Legume 
{
    @Override
    public Varietes getVariete() 
    {
        return Varietes.SALADE;
    }

    @Override
    protected void croissance() 
    {
        niveau_croissance += 4;
    }
}
