package modele.item;

public abstract class Item 
{
    private int quantite;
    private float prix_vente;
    private float prix_achat;
    public Item(int _quantite, float _prix_achat)
    {
        quantite   = _quantite;
        prix_achat = _prix_achat;
        prix_vente = _prix_achat / 2;
    }

    public int get_quantite()
    {
        return quantite;
    }
    public void ajouter_quantite(int q)
    {
        quantite += q;
    }
    public void baisser_quantiter(int q)
    {
        if(quantite < q) return;
        quantite -= q;
    }

    public float get_prix_vente()
    {
        return prix_vente;
    }

    public float get_prix_achat()
    {
        return prix_achat;
    }

    public boolean utliser()
    {
        if(quantite <= 0) return false;
        baisser_quantiter(1);
        return true;
    }

    

}
