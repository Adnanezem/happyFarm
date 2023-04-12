package modele.item;

public abstract class Item 
{
    private int quantite;
    private int prix_vente;
    private int prix_achat;
    public Item(int _quantite, int _prix_achat, int _prix_vente)
    {
        quantite = _quantite;
        prix_achat = _prix_achat;
        prix_vente = _prix_vente;
    }

    public void ajouter_quantite(int q)
    {
        quantite += q;
    }
    public void baisser_quantiter(int q)
    {
        quantite -= q;
    }

    public int get_prix_vente()
    {
        return prix_vente;
    }
    public int get_prix_achat()
    {
        return prix_achat;
    }

}
