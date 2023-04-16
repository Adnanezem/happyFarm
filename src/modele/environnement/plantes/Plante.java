package modele.environnement.plantes;

public abstract class Plante 
{
    public abstract Varietes getVariete();
    protected int niveau_croissance;
    protected EtatCroissance state;
    public Plante()
    {
        niveau_croissance = 0;
        state = EtatCroissance.GERM;
    }
    public void nextStep() 
    {
        croissance();
        verification_etat_plante();
    }

    protected abstract void croissance(); 

    private void verification_etat_plante()
    {
        if(niveau_croissance > 20)
        {
            state = EtatCroissance.POURI;
        }
        else if(niveau_croissance > 10)
        {
            state = EtatCroissance.MUR;
        }
    }

    public EtatCroissance get_etat_plante()
    {
        return state;
    }

}
