package modele.environnement;

import modele.Subscriber;

import modele.environnement.plantes.Plante;
import modele.environnement.plantes.legumes.*;
import modele.environnement.plantes.fruits.*;
import modele.item.graines.Graine;

public class CaseCultivable extends Case implements Subscriber
{
    private Plante plante;
    protected float fertilite;
    
    public CaseCultivable()
    {
        super();
        fertilite = 5;
    }
    public Plante get_plante() 
    {
        return plante;
    }

    public void update()
    {
        if(plante != null)
        {
            plante.nextStep();
        }
        if(fertilite > 0)
        {
            fertilite -= 0.2;
        }

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
}
