package modele.environnement.plantes.fruits;

import modele.environnement.plantes.Varietes;

public class Coco extends Fruit 
{

    @Override
    public Varietes getVariete() 
    {
        return Varietes.COCO;
    }

    @Override
    protected void croissance() 
    {
        niveau_croissance += 1;
    }

}