package modele.item.outils;

public class Shovel extends Outil
{
    public Shovel( int _quantite, int _prix_achat, int _prix_vente, Instrument _type)
    {
        super( _quantite,  _prix_achat,  _prix_vente, _type);
        deterioration_factor = 5f;
    }  
    
    
}
