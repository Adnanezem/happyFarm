package modele.item.outils;

public class Shovel extends Outil
{
    public Shovel( int _quantite, float _prix_achat)
    {
        super( _quantite,  _prix_achat);
        type = Instrument.SHOVEL;
        deterioration_factor = 5f;
    }  
    
    
}
