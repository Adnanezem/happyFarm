package modele;

import java.util.Vector;

import modele.environnement.plantes.Varietes;
import modele.item.Item;
import modele.item.graines.Graine;
import modele.item.outils.Outil;
import modele.meteo.SimulateurMeteo;

public class Simulation 
{
    private Minuteur clock;
    private SimulateurMeteo meteo;    
    private SimulateurPotager potager;
    private Inventaire stock;
    private Market market;
    public Simulation()
    {
        Minuteur clock = new Minuteur(1000);
        SimulateurMeteo meteo = new SimulateurMeteo(clock);    
        SimulateurPotager potager = new SimulateurPotager(clock, meteo);
        Inventaire stock = new Inventaire();
        Market market = new Market(20, clock, stock);
    }

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

    public Vector<Outil> get_tools_list_in_inventory()
    {
        return stock.get_outil_disponible();
    }

    

    public void planter(Graine g, int x, int y)
    {
        if(!stock.est_dispo(g , 1)) return;
        stock.retirer_item(g, 1);
        potager.planter(x , y , g);
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


    public static void main(String[] args) 
    {
        Simulation s = new Simulation();
    }
}
