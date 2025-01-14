package modele;
import java.util.Vector;

import modele.environnement.plantes.Plante;
import modele.environnement.plantes.Varietes;
import modele.item.Engrais;
import modele.item.Item;
import modele.item.graines.Graine;
import modele.item.outils.Outil;
public class Inventaire 
{
    private Vector<Box> plant_boxes;
    private Vector<Outil> outils_disponible;
    private Graine[] graines_disponible;
    private Engrais engrais;
    private Vector<Item> item_possedes;
    
    //Contructeur
    public Inventaire()
    {
        item_possedes = new Vector<Item>();
        plant_boxes   = new Vector<Box>();
        outils_disponible = new Vector<Outil>();
        init_graines();
        engrais = new Engrais(2, 0);
        item_possedes.add(engrais);
    }   
    
    //Fonction qui initialise les graines disponibles dans l'inventaire
    private void init_graines()
    {
        graines_disponible    = new Graine[Varietes.values().length];
        int i = 0;
        for (Varietes v : Varietes.values()) 
        {
            graines_disponible[i] = new Graine(v, 1000 , 1);
            item_possedes.add(graines_disponible[i]);
            i++;
        }
    }
    
    //Fonction qui renvoi "true" si une quantité d'un item est disponible dans l'inventaire
    public boolean est_dispo(Item item_to_check, int quantite)
    {
        print_items();
        if(item_to_check instanceof Graine) return est_dispo_graine((Graine)item_to_check, quantite);
        for (Item item : item_possedes) 
        {
            if(item.getClass().equals(item_to_check.getClass()))
            {
                if (item.get_quantite() >= quantite) 
                {
                    return true;    
                }
                return false;
            }    
        }
        return false;
    }
    
    //Fonction qui renvoi "true" si une quantité d'une graine est disponible dans l'inventaire
    private boolean est_dispo_graine(Graine graine, int quantite)
    {
        for (Item item : item_possedes) 
        {
            if(item instanceof Graine)
            {
                Graine temp = (Graine) item;
                if(temp.get_variete() == graine.get_variete())
                {
                    if( temp.get_quantite() >= quantite) return true;
                    else return false;
                }


            }
        }
        return false;
    }
    
    //Fonction ajoutant un item dans l'inventaire
    public void add_item(Item item_to_add)
    {
        if(item_to_add instanceof Outil)
        {
            Outil temp = (Outil) item_to_add;
            outils_disponible.add(temp);
            item_possedes.add(temp);
            return;
        }
        for (Item item : item_possedes) 
        {
            if(item.getClass().equals(item_to_add.getClass()))
            {
                item.ajouter_quantite(item_to_add.get_quantite());
                return;
            }   
        }
    }
    
    //Fonction ajoutant une plante dans l'inventaire
    public void add_plante(Plante plant_to_add)
    {
        if(plant_to_add == null) return;
        for (Box b : plant_boxes) 
        {
            if(b.get_quantite() == 0)
            {
                b.add(plant_to_add);
                return;
            }
            if(b.get_variete().equals(plant_to_add.getVariete()) && !b.is_full())
            {
                b.add(plant_to_add);
            }
        }
        Box temp = new Box();
        temp.add(plant_to_add);
        plant_boxes.add(temp);
    }
    
    //Fonction retirant une plante dans l'inventaire
    public Box retirer_plante(Varietes v)
    {
        int i = 0;
        for (Box b : plant_boxes) 
        {
            if(b.get_variete().equals(v))
            {
                return plant_boxes.remove(i);
            }
            i++;
        }
        return null;
    }
    
    //Fonction retirant un item dans l'inventaire
    public float retirer_item(Item item_a_retirer, int quantite)
    {
        if(item_a_retirer instanceof Graine) return retirer_graine((Graine) item_a_retirer, quantite);
        for (Item item : item_possedes) 
        {
            if(item.getClass().equals(item_a_retirer.getClass()))
            {
                item.baisser_quantiter(quantite);
                return item.get_prix_vente() * quantite;
            }    
        }
        return 0;
    }
    //Fonction retirant une graine dans l'inventaire
    private float retirer_graine(Graine graine, int quantite)
    {
        
        for (Item item : item_possedes) 
        {
            if(item instanceof Graine)
            {
                Graine temp = (Graine) item;
                if(temp.get_variete() == graine.get_variete())
                {
                    temp.baisser_quantiter(quantite);
                    return item.get_prix_vente() * quantite;
                }    
            }
        }
        return 0;
    }
    
    public void print_items()
    {
        for (Item graine : item_possedes) {
            System.out.print(graine.get_quantite()+" ");
        }
        System.out.println();
    }
    
    public Vector<Outil> get_outil_disponible()
    {
        return outils_disponible;
    }

    public Vector<Box> get_plant_boxes()
    {
        return plant_boxes;
    }

    public Vector<Item> get_item_disponible()
    {
        return item_possedes;
    }

    public Graine[] get_graine_disponible()
    {
        return graines_disponible;
    }
    
    
    public Integer get_number_of_graines(Varietes v)
    {
        for (Item g : item_possedes) 
        {
            if(!(g instanceof Graine)) continue;
            if(((Graine)g).get_variete() == v)
            {
                return g.get_quantite();
            }
        }
        return 0;
    }
    public int get_total_graines()
    {
        int total = 0;
        for (Item g : item_possedes) 
        {
            if(!(g instanceof Graine)) continue;
            total += g.get_quantite();
        }
        return total;
    }
}
