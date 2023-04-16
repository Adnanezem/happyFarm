package modele.item.outils;

public class Pickaxe extends Outil
{
    public Pickaxe( int _quantite, float _prix_achat)
    {
        super( _quantite,  _prix_achat);
        type = Instrument.PICKAXE;
        deterioration_factor = 1f;
    }  
}
