package modele.environnement.plantes.legumes;

import modele.environnement.plantes.Varietes;

public class Courgette extends Legume 
{

    @Override
    public Varietes getVariete() 
    {
        return Varietes.COURGETTE;
    }

    @Override
    protected void croissance() 
    {
        niveau_croissance += 4;
    }

}