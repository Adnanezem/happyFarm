package modele.environnement.plantes.fruits;

import modele.environnement.plantes.Varietes;

public class Tomate extends Fruit 
{

    @Override
    public Varietes getVariete() 
    {
        return Varietes.TOMATE;
    }

    @Override
    protected void croissance() 
    {
        niveau_croissance += 4;
    }

}