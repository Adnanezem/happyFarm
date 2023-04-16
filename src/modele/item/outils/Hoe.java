package modele.item.outils;

public class Hoe extends Outil 
{
    public Hoe( int _quantite, float _prix_achat)
    {
        super( _quantite,  _prix_achat);
        type = Instrument.HOE;
        deterioration_factor = 2.5f;
    }  
    void Hoe_action()
    {
        if(durabilite == 0) return;
        durabilite -= deterioration_factor;
        if(durabilite <= 0)
        {
            durabilite = 0;
        }
    }
}
