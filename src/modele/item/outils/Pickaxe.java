package modele.item.outils;

public class Pickaxe extends Outil
{
    public Pickaxe( int _quantite, int _prix_achat, int _prix_vente, Instrument _type)
    {
        super( _quantite,  _prix_achat,  _prix_vente, _type);
        deterioration_factor = 1f;
    }  
}
