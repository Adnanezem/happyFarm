package modele.item.outils;
import modele.item.Item;
public abstract class Outil extends Item
{
    protected Instrument type;
    protected float durabilite;
    protected float deterioration_factor;
    public Outil(int _quantite, float _prix_achat)
    {
        super( _quantite, _prix_achat);
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
