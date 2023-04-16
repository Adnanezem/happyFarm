package modele;
import modele.environnement.plantes.Varietes;
import modele.item.Engrais;
import modele.item.Item;
import modele.item.graines.Graine;
import modele.item.outils.Hoe;
import modele.item.outils.Instrument;
import modele.item.outils.Outil;
import modele.item.outils.Pickaxe;
import modele.item.outils.Shovel;
import java.util.Vector;

public class Market implements Subscriber
{
    private int refresh_rate;
    private int refresh_counter;
    private Vector<Item> item_disponible;
    private Outil[] outils_disponible;
    private Graine[] graines_disponible;
    private Engrais engrais;
    private float balance;
    private Inventaire stock;

    public Market(float starter_balance)
    {   
        refresh_rate = 5;
        refresh_counter = 0;
        item_disponible = new Vector<Item>();
        init_outils();
        init_graines();
        engrais = new Engrais(100, 2);
        item_disponible.add(engrais);
        balance = starter_balance;
        stock = new Inventaire();

    }

    private void init_outils()
    {
        outils_disponible    = new Outil[Instrument.values().length];
        outils_disponible[0] = new Hoe(1 , 10 );
        outils_disponible[1] = new Shovel(1, 15);
        outils_disponible[2] = new Pickaxe(1, 15);
        for (Item o : outils_disponible) 
        {
            item_disponible.add(o);    
        }
    }

    private void init_graines()
    {
        graines_disponible    = new Graine[Varietes.values().length];
        graines_disponible[0] = new Graine(Varietes.CAROTTE, 50 , 1);
        graines_disponible[1] = new Graine(Varietes.COURGETTE, 50 , 1);
        graines_disponible[2] = new Graine(Varietes.PATATE, 50 , 1);
        graines_disponible[3] = new Graine(Varietes.SALADE, 50 , 1);
        graines_disponible[4] = new Graine(Varietes.COCO, 50 , 1);
        graines_disponible[5] = new Graine(Varietes.ORANGE, 50 , 1);
        graines_disponible[6] = new Graine(Varietes.TOMATE, 50 , 1);
        graines_disponible[7] = new Graine(Varietes.RAISIN, 50 , 1);
        for (Item o : graines_disponible) 
        {
            item_disponible.add(o);    
        }
    }
    public boolean acheter(Item item_a_acheter, int quantite)
    {
        float cout = item_a_acheter.get_prix_achat() * quantite;
        if(balance < cout)
        {
            return false;
        }
        if(item_a_acheter instanceof Outil)
        {
            return acheter_outil(item_a_acheter, quantite);
        }
        if(item_a_acheter instanceof Graine)
        {
            return acheter_graine(item_a_acheter, quantite);
        }
        if(item_a_acheter instanceof Engrais)
        {
            if(engrais.get_quantite() < quantite) return false;
            if(engrais.get_prix_achat() * quantite > balance) return false;
            engrais.baisser_quantiter(quantite);
            //rajouter au stock
            return true;
        }
        return true;
    }
    private boolean acheter_outil(Item item_a_acheter, int quantite)
    {
        for (Outil o : outils_disponible) 
        {
            if(o.getClass().equals(item_a_acheter.getClass()))
            {
                if(quantite > o.get_quantite()) return false;
                else if(quantite * o.get_prix_achat() > balance) return false;
                o.baisser_quantiter(quantite);
                balance -= quantite * o.get_prix_achat();
                //TODO : rajouter a l'inventaire
                return true;
            }
        }
        return false;
    }
    private boolean acheter_graine(Item g, int quantite)
    {
        Graine temp = (Graine) g;
        for (Graine o : graines_disponible) 
        {
            if(o.get_variete().equals(temp.get_variete()))
            {
                if(quantite > o.get_quantite()) return false;
                else if(quantite * o.get_prix_achat() > balance) return false;
                o.baisser_quantiter(quantite);
                balance -= quantite * o.get_prix_achat();
                stock.add_item(new Graine(temp.get_variete(), quantite, o.get_prix_achat()));
                return true;
            }
        }
        return false;
    }

    public void vendre(Item item_a_vendre, int quantite)
    {
        float benef = item_a_vendre.get_prix_vente() * quantite;
        balance += benef;
        //TODO : retirer de l'inventaire
    }

    public void update()
    {
        if (refresh_counter != 0) 
        {
            refresh_counter --;
            return;
        }
        for (Item o : item_disponible) 
        {
            double temp = Math.random() * ( 100 - 0 );
            if (temp < 50) 
            {
                o.ajouter_quantite((int) Math.random() * ( 5 - 1 ));
            }    
        }
        refresh_counter = refresh_rate;
    }
    
    
}
