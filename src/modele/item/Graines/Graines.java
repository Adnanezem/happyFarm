package modele.item.Graines;

import modele.environnement.varietes.Varietes;
import modele.item.Item;

public class Graines extends Item 
{
    private Varietes variete;
    Graines(int _quantite, int _prix_achat, int _prix_vente, Varietes _variete)
    {
        super( _quantite,  _prix_achat,  _prix_vente);
        variete = _variete;
    }    

    public Varietes get_variete()
    {
        return variete;
    }
}
