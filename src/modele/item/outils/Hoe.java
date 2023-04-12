package modele.item.outils;

public class Hoe extends Outil 
{
    public Hoe( int _quantite, int _prix_achat, int _prix_vente, Instrument _type)
    {
        super( _quantite,  _prix_achat,  _prix_vente, _type);
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
