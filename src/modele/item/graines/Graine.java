package modele.item.graines;

import modele.environnement.plantes.Varietes;

public class Graine 
{
    private Varietes variete;
    public Graine(Varietes v)
    {
        variete = v;
    }

    public Varietes get_variete() 
    {
        return variete;
    }
}
