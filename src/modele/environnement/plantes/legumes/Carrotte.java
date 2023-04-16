package modele.environnement.plantes.legumes;

import modele.environnement.plantes.Varietes;

public class Carrotte extends Legume 
{

    @Override
    public Varietes getVariete() 
    {
        return Varietes.CAROTTE;
    }

    @Override
    protected void croissance() 
    {
        niveau_croissance += 2;
    }

}
