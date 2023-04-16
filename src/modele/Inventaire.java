package modele;
import modele.environnement.plantes.Plante;
import modele.item.Item;

import java.util.Vector;
public class Inventaire 
{
    private Vector<Item> item_dispo;
    private Vector<Plante> plantes_possedes;
    private int taille_max;
    private int taille_utilise;
    public Inventaire()
    {
        taille_utilise = 0;
        taille_max = 20;
        item_dispo = new Vector<Item>();
        plantes_possedes = new Vector<Plante>();
    }    
    public boolean est_dispo(Item item_to_check, int quantite)
    {
        //TODO : check if typeof() return true if the comparison is of type (childClass.typeof(parentClass))
        return true;
    }

    public void add_to_stock(Item item_to_add)
    {
        //TODO : if the item exists : old quantite + item_to_add.quantite; return;
        //if taille_max == taille_courrante : return;
        //else item_dispo.add(item_to_add);
    }

}
