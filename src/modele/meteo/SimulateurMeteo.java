package modele.meteo;
import modele.Minuteur;
import modele.SimulateurPotager;
import modele.Subscriber;

public class SimulateurMeteo implements Subscriber
{
    private int MORNING_TIME = 12;
    private int NOON_TIME    = 18;
    private int NIGHT_TIME   = 8;
    private int temperature;
    private int humidite;
    private int ensolleillement;
    private int day_time;
    private boolean auto_simulate;
    private Date calendrier;
    public SimulateurMeteo()
    {
        temperature     = 20;
        humidite        = 50;
        ensolleillement = 50;
        day_time        = 8;
        auto_simulate   = true;
        calendrier = new Date();
    }
    public SimulateurMeteo(int _temperature, int _humidite, int _ensolleiment ) 
    {
        temperature     = _temperature;
        humidite        = _humidite;
        ensolleillement = _ensolleiment;
        day_time        = 8;
        auto_simulate   = true;
        calendrier      = new Date();
    }   
    private void update_ensolleiment()
    {
        if(!auto_simulate) return;
        if(day_time <= MORNING_TIME)
        {
            ensolleillement = 70;
        }
        else if(day_time <= NOON_TIME)
        {
            ensolleillement = 100;
        }
        else if(day_time <= NIGHT_TIME)
        {
            ensolleillement = 10;
        }
    }
    private void update_humidite()
    {
        if(!auto_simulate) return;
        if(day_time <= MORNING_TIME)
        {
            humidite = 70;
        }
        else if(day_time <= NOON_TIME)
        {
            humidite = 100;
        }
        else if(day_time <= NIGHT_TIME)
        {
            humidite = 10;
        }
    }
    private void update_temperature()
    {
        if(!auto_simulate) return;
        if(day_time <= MORNING_TIME)
        {
            temperature = 15;
        }
        else if(day_time <= NOON_TIME)
        {
            temperature = 30;
        }
        else if(day_time <= NIGHT_TIME)
        {
            temperature = 20;
        }
    }


    public void auto_simulate()
    {
        if(auto_simulate)
        {
            auto_simulate = false;
        }
        else 
        {
            auto_simulate = true;
        }
    }

    public void update()
    {

        update_ensolleiment();
        update_humidite();
    }

    public int get_temperature()
    {
        return temperature;
    }
    public int get_humidite()
    {
        return humidite;
    }
    public int get_ensolleillment()
    {
        return ensolleillement;
    }

}
