package modele;
import java.util.Vector;

import modele.environnement.plantes.Varietes;
import modele.item.Engrais;
import modele.item.Item;
import modele.item.graines.Graine;
import modele.item.outils.Hoe;
import modele.item.outils.Outil;
import modele.item.outils.Pickaxe;
import modele.item.outils.Shovel;

public class Market implements Subscriber
{
    //constants 
    private float shovel_price  = 15;
    private float hoe_price     = 10;
    private float pickaxe_price = 10; 
    private int refresh_rate    = 5;
    private int refresh_counter = refresh_rate;


    private Vector<Item> item_disponible;
    private Graine[] graines_disponible;
    private Engrais engrais;
    private float balance;
    private Inventaire stock;

    public Market(float starter_balance, Minuteur m, Inventaire _stock)
    {   
        item_disponible = new Vector<Item>();
        init_graines();
        engrais = new Engrais(100, 2);
        item_disponible.add(engrais);
        balance = starter_balance;
        stock = _stock;
        m.add_subscriber(this);
    }

    public Vector<Item> get_item_disponible()
    {
        return item_disponible;
    }

    public Graine[] get_graines_disponible()
    {
        return graines_disponible;
    }

    public float get_balance()
    {
        return balance;
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
            return acheter_outil(item_a_acheter);
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
    private boolean acheter_outil(Item item_a_acheter)
    {
        if(item_a_acheter instanceof Shovel)
        {
            if(balance < shovel_price) return false;
            Shovel temp = new Shovel(1 , shovel_price);
            balance -= shovel_price;
            stock.add_item(temp);
            return true;
        }

        if(item_a_acheter instanceof Hoe)
        {
            if(balance < hoe_price) return false;
            Hoe temp = new Hoe(1 , hoe_price);
            balance -= hoe_price;
            stock.add_item(temp);
            return true;
        }

        if(item_a_acheter instanceof Pickaxe)
        {
            if(balance < pickaxe_price) return false;
            Pickaxe temp = new Pickaxe(1 , pickaxe_price);
            balance -= pickaxe_price;
            stock.add_item(temp);
            return true;
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

    public boolean vendre_item(Item item_a_vendre, int quantite)
    {
        if(item_a_vendre instanceof Outil) return false;
        float benef = stock.retirer_item(item_a_vendre, quantite);
        if(benef == 0)
        {
            return false;
        }
        balance += benef;
        return true;
    }

    public boolean vendre_plante(Varietes v )
    {
        Box temp = stock.retirer_plante(v);
        float benef = temp.get_prix();
        if(benef == 0) return false;
        balance += benef;
        return true;
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
