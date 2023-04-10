package modele.environnement.varietes;

public abstract class Plante 
{
    public abstract Varietes getVariete();
    private int niveau_croissance;
    public Plante()
    {
        niveau_croissance = 0;
        
    }
    public void nextStep() 
    {
        croissance();
    }

    protected abstract void croissance(); 
}
