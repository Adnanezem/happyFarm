package modele.environnement.plantes.fruits;

import modele.environnement.plantes.Varietes;

public class Orange extends Fruit 
{

    @Override
    public Varietes getVariete() 
    {
        return Varietes.ORANGE;
    }

    @Override
    protected void croissance() 
    {
        niveau_croissance += 3;
    }

}