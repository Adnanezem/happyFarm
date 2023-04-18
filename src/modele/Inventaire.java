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
    public Inventaire()
    {
        item_possedes = new Vector<Item>();
        plant_boxes   = new Vector<Box>();
        outils_disponible = new Vector<Outil>();
        init_graines();
        engrais = new Engrais(2, 0);
        item_possedes.add(engrais);
    }   

    private void init_graines()
    {
        graines_disponible    = new Graine[Varietes.values().length];
        int i = 0;
        for (Varietes v : Varietes.values()) 
        {
            graines_disponible[i] = new Graine(v, 1 , 1);
            item_possedes.add(graines_disponible[i]);
            i++;
        }
    }

    public boolean est_dispo(Item item_to_check, int quantite)
    {
        for (Item item : item_possedes) 
        {
            if(item.getClass().equals(item_to_check.getClass()))
            {
                if (item.get_quantite() == quantite) 
                {
                    return true;    
                }
                return false;
            }    
        }
        return false;
    }

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

    public void add_plante(Plante plant_to_add)
    {
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

    public float retirer_item(Item item_a_retirer, int quantite)
    {
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
    
    public Vector<Outil> get_outil_disponible()
    {
        return outils_disponible;
    }
}
