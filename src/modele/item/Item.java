package modele.item;

public abstract class Item {
    private int quantite;
    private int prix_vente;
    private int prix_achat;
    public Item(int _quantite, int _prix_achat, int _prix_vente)
    {
        quantite = _quantite;
        prix_achat = _prix_achat;
        prix_vente = _prix_vente;
    }

    void ajouter_quantite(int q)
    {
        quantite += q;
    }
    void baisser_quantiter(int q)
    {
        quantite -= q;
    }

}
