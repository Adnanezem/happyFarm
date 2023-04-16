package modele.environnement.plantes.fruits;

import modele.environnement.plantes.Varietes;

public class Raisin extends Fruit 
{

    @Override
    public Varietes getVariete() 
    {
        return Varietes.RAISIN;
    }

    @Override
    protected void croissance() 
    {
        niveau_croissance += 5;
    }

}