package modele.environnement;


import modele.Subscriber;

import modele.environnement.plantes.Plante;
import modele.environnement.plantes.legumes.*;
import modele.environnement.plantes.fruits.*;
import modele.item.graines.Graine;
import modele.meteo.SimulateurMeteo;

public class CaseCultivable extends Case implements Subscriber
{
    private Plante plante;
    private float fertilite;
    SimulateurMeteo meteo;
    
    public CaseCultivable(SimulateurMeteo _meteo)
    {
        super();
        fertilite = 5;
        meteo = _meteo;
    }
    public Plante get_plante() 
    {
        return plante;
    }

    public void update()
    {
        if(fertilite > 0)
        {
            fertilite -= 0.2;
        }
        follow_weather();
        if(plante == null || !growth_conditions()) return;
        
        plante.nextStep();
        
    }

    private boolean growth_conditions()
    {
        if(temperature < 0) return false;
        if(temperature > 45) return false;
        if(humidite > 90) return false;
        if(humidite < 10) return false;
        if(fertilite < 0) return false;
        return true;
    }

    private void follow_weather()
    {
        float humidite_bonus = 0.3f;
        if(meteo.is_raining()) humidite_bonus = 0.7f;
        humidite = lerp(humidite, meteo.get_humidite(), humidite_bonus);
        temperature = lerp(temperature , meteo.get_temperature(), 0.3f);
    }

    private int lerp(int a, int b, float t) 
    {
        return (int) (a + (b - a) * t);
    }
    
    public void set_plante(Graine graine) {
        switch (graine.get_variete()) {
            case CAROTTE:
                plante = new Carrotte();
                break;
            case COURGETTE:
                plante = new Courgette();
                break;
            case PATATE:
                plante = new Patate();
                break;
            case COCO:
                plante = new Coco();
                break;
            case ORANGE:
                plante = new Orange();
                break;
            case SALADE:
                plante = new Salade();
                break;
            case RAISIN:
                plante = new Raisin();
                break;
            case TOMATE:
                plante = new Tomate();
                break;
            default:
                throw new IllegalArgumentException("Unknown variété: " + graine.get_variete());
        }
    }
    public float get_fertilite()
    {
        return fertilite;
    }

    public Plante recolter_plante()
    {
        if(plante == null ) return null;
        Plante temp = plante;
        plante = null;
        //TODO : verifier qu'on ne met pas l'instance a null mais seulement cette reference là
        return temp;
    }
}
