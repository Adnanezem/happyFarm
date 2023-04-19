package modele.environnement.plantes;

public abstract class Plante implements Cloneable
{
    protected int niveau_croissance;
    protected EtatCroissance state;


    public Plante()
    {
        niveau_croissance = 0;
        state = EtatCroissance.GERM;
    }

    public Plante(Plante p)
    {
        niveau_croissance = p.get_niveau_croissance();
        state = p.get_etat_plante();
    }

    public abstract Varietes getVariete();
    public abstract float get_prix();
    protected abstract void croissance(); 
    public int get_niveau_croissance()
    {
        return niveau_croissance;
    }
    public void nextStep() 
    {
        croissance();
        verification_etat_plante();
    }

    public EtatCroissance get_etat_plante()
    {
        return state;
    }


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

    @Override
    public Plante clone() throws CloneNotSupportedException 
    {
        Plante clone = (Plante) super.clone();
        return clone;
    }


}