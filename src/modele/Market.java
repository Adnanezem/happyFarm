package modele;
import modele.item.Item;
public class Market 
{
    private Item[] items_disponible;
    private int balance;
    private Inventaire stock;

    public Market(int starter_balance)
    {
        //TODO : define starter items and a way to update 
        balance = starter_balance;
    }

    public void acheter(Item item_a_acheter, int quantite)
    {
        int cout = item_a_acheter.get_prix_achat() * quantite;
        if(balance < cout)
        {
            return; //TODO : can be better then this shit
        }
        balance -= cout;
        item_a_acheter.baisser_quantiter(quantite);

    }

    public void vendre(Item item_a_vendre, int quantite)
    {
        int benef = item_a_vendre.get_prix_vente() * quantite;
        balance += benef;
        //TODO : retirer de l'inventaire
    }

}
