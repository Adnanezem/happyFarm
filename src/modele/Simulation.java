package modele;


import java.util.Vector;

import modele.environnement.Case;
import modele.environnement.plantes.Plante;
import modele.environnement.plantes.Varietes;
import modele.item.Item;
import modele.item.graines.Graine;
import modele.item.outils.Instrument;
import modele.item.outils.Outil;
import modele.meteo.SimulateurMeteo;

public class Simulation 
{
    public Minuteur clock;
    public SimulateurMeteo meteo;    
    public SimulateurPotager potager;
    public Inventaire stock;
    public Market market;
    public boolean is_running = true;
    private Varietes choixPlante;
	private Instrument choixOutil;
    public Simulation()
    {
        clock = new Minuteur(1000);
        meteo = new SimulateurMeteo(clock);   
        potager = new SimulateurPotager(clock, meteo, 20 , 10);
        stock = new Inventaire();
        market = new Market(20, clock, stock);
    }

    public void stop_simulation()
    {
        clock.stop_clock();
        is_running = false;
    }

    public boolean get_is_running()
    {
        return is_running;
    }
    public Varietes getChoixPlante() 
    {
    	return this.choixPlante;
    }
    
    public void setChoixPlante(Varietes var) 
    {
    	this.choixPlante = var;
    	this.choixOutil = null;
    }
    
    public Instrument getChoixOutil() 
    {
    	return this.choixOutil;
    }
    
    public void setChoixOutil(Instrument inst) {
    	this.choixOutil = inst;
    	this.choixPlante = null;
    }


    //clock methods :
    public void pause_simulation()
    {
        clock.pause();
    }

    public void accelerate()
    {
        clock.speed_up();
    }

    public void decelerate()
    {
        clock.slow_down();
    }

    //weather methods : 
    public void toggle_auto_weather()
    {
        meteo.auto_simulate();
    }

    public void change_temperature(int t)
    {
        meteo.set_temperature(t);
    }

    public void change_humidite(int h)
    {
        meteo.set_humidite(h);
    }

    public void change_ensoleillement(int e)
    {
        meteo.set_ensoleillement(e);
    }

    public void toggle_rain()
    {
        meteo.set_rain();
    }



    //inventory methods :

    public Vector<Outil> get_tools_list_in_inventory()
    {
        return stock.get_outil_disponible();
    }

    public Vector<Item> get_item_list_in_inventory()
    {
        return stock.get_item_disponible();
    }

    public Vector<Box> get_plants_in_inventory()
    {
        return stock.get_plant_boxes();
    }

    public Graine[] get_graine_in_inventory()
    {
        return stock.get_graine_disponible();
    }


    // market methods : 

    public Vector<Item> get_item_in_market()
    {
        return market.get_item_disponible();
    }
    public float get_balance()
    {
        return market.get_balance();
    }

    public Graine[] get_graine_in_market()
    {
        return market.get_graines_disponible();
    }
    
    public boolean acheter(Item item, int quantite)
    {
        return market.acheter(item, quantite);
    }

    public boolean vendre_item(Item item, int quantite)
    {
        return market.vendre_item(item, quantite);
    }

    public boolean vendre_plante(Varietes v)
    {
        return market.vendre_plante(v);
    }
    



    //potager methods : 

    public void planter(Graine g, int x, int y)
    {
        assert g != null;
        if(!stock.est_dispo(g , 1)) return;
        stock.retirer_item(g, 1);
        potager.planter(x , y , g);
    }
    public Case[][] get_plateau()
    {
        return potager.getPlateau();
    }

    public void recolter_plante(int x, int y)
    {
        Plante temp = potager.recolter_plante(x, y);
        stock.add_plante(temp);
    }

    public int get_size_x()
    {
        return potager.SIZE_X;
    }

    public int get_size_y()
    {
        return potager.SIZE_Y;
    }

    public void actionUtilisateur(int x, int y) 
    {
        if(choixOutil != null)
        {
            utiliserOutil(x, y);
            return;
        }
        planter(new Graine(choixPlante, 1, 0) , x , y  );
    }

     //fonctions qui permets de labourer une case qui est non cultivable   
     public void utiliserOutil(int x, int y) 
     {
        if (choixOutil != null) 
        {
            switch(choixOutil) 
            {
                case HOE: potager.labourer(x, y); break;
                case PICKAXE: break;
                case SHOVEL: break;
                default: return; 
            }
        }
    }

}
