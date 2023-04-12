package modele.item.outils;
import modele.item.Item;
public abstract class Outil extends Item
{
    protected Instrument type;
    protected float durabilite;
    protected float deterioration_factor;
    public Outil(int _quantite, int _prix_achat, int _prix_vente,Instrument _type )
    {
        super( _quantite, _prix_achat, _prix_vente);
        type = _type;
        durabilite = 100;
    }

    public float get_durabilite()
    {
        return durabilite;
    }
    void utiliser()
    {
        if(durabilite == 0) return;
        durabilite -= deterioration_factor;
        if(durabilite <= 0)
        {
            durabilite = 0;
        }
    }

}
