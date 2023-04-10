package modele.environnement.varietes;

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
        
    }
}
