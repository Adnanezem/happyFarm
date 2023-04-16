package modele.item.graines;

import modele.environnement.plantes.Varietes;
import modele.item.Item;

public class Graine extends Item
{
    private Varietes variete;
    public Graine(Varietes v, int _quantite, float _prix_achat)
    {
        super(_quantite, _prix_achat);
        variete = v;
    }

    public Varietes get_variete() 
    {
        return variete;
    }
}
