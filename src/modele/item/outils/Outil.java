package modele.item.outils;

public abstract class Outil 
{
    private Instrument type;
    private float durabilte;
    public Outil(Instrument _type)
    {
        type = _type;
        durabilte = 100
    }

    public get_durabilite()
    {
        return durabilte;
    }

    public void utiliser()
    {

    }
}
