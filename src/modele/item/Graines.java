package modele.item;

import modele.environnement.varietes.Varietes;

public class Graines extends Item 
{
    private Varietes variete;
    Graines(int _quantite, int _prix_achat, int _prix_vente, Varietes _variete)
    {
        super( _quantite,  _prix_achat,  _prix_vente);
        variete = _variete;
    }    
}
