package modele.meteo;
import modele.Minuteur;
import modele.Subscriber;

public class SimulateurMeteo implements Subscriber
{
    private int temperature;
    private int humidite;
    private int ensoleillement;
    private boolean pluit = false;
    private int rain_timer = 0;
    private boolean auto_simulate;
    public Date calendrier;
    public SimulateurMeteo(Minuteur m)
    {

        temperature     = 20;
        humidite        = 50;
        ensoleillement  = 50;
        auto_simulate   = true;
        calendrier      = new Date(m);
        m.add_subscriber(this);
    }
    public SimulateurMeteo(int _temperature, int _humidite, int _ensoleillement, Minuteur m ) 
    {
        temperature     = _temperature;
        humidite        = _humidite;
        ensoleillement  = _ensoleillement;
        auto_simulate   = true;
        calendrier      = new Date(m);
        m.add_subscriber(this);
    }   
    private void update_ensoleillement()
    {
        if(!auto_simulate) return;
        if(pluit) 
        {
            ensoleillement = 10;
            return;
        }
        DayTime temp = calendrier.get_day_time();
        if(temp == DayTime.MORNING_TIME)
        {
            ensoleillement = 70;
        }
        else if(temp == DayTime.NOON_TIME)
        {
            ensoleillement = 100;
        }
        else if(temp == DayTime.NIGHT_TIME)
        {
            ensoleillement = 10;
        }
        else
        {
            throw new IllegalArgumentException("Unknown DayTime: " + temp);
        }
    }
    private void random_rain()
    {
        Saison current_season = calendrier.get_season();
        double temp = Math.random() * ( 100 - 0 );
        switch (current_season) {
            case HIVER:
                if(temp < 20)
                {
                    pluit = true;
                    rain_timer = 15;
                }
                break;
            case AUTUMN:
                if(temp < 50)
                {
                    pluit = true;
                    rain_timer = 10;
                }
                break;
            case PRINTEMPS:
                if(temp < 50)
                {
                    pluit = true;
                    rain_timer = 25;
                }
                break;
            case ETE:
                if(temp < 80)
                {
                    pluit = true;
                    rain_timer = 5;
                }
                break;        
        
            default:
                throw new IllegalArgumentException("Unknown season: " + temp);
                
        }
    }

    private void update_rain()
    {
        if(pluit == true)
        {
            rain_timer --;
            if(rain_timer == 0)
            {
                pluit = false;
            }
            return;
        }
        random_rain();
    }

    private void update_humidite()
    {
        if(!auto_simulate) return;
        DayTime temp = calendrier.get_day_time();
        int rain_bonus = 0;
        if(pluit)
        {
            rain_bonus = 50;
        }
        if(temp == DayTime.MORNING_TIME)
        {
            humidite = 25 + rain_bonus;
        }
        else if(temp == DayTime.NOON_TIME)
        {
            humidite = 10 + rain_bonus;
        }
        else if(temp == DayTime.NIGHT_TIME)
        {
            humidite = 30 + rain_bonus;
        }
        else
        {
            throw new IllegalArgumentException("Unknown DayTime: " + temp);
        }
    }
    private void update_temperature()
    {
        if(!auto_simulate) return;
        DayTime temp = calendrier.get_day_time();
        int bonus_season = get_temperature_bonus_season(calendrier.get_season());
        if(temp == DayTime.MORNING_TIME)
        {
            temperature = 7 + bonus_season;
        }
        else if(temp == DayTime.NOON_TIME)
        {
            temperature = 20 + bonus_season;
        }
        else if(temp == DayTime.NIGHT_TIME)
        {
            temperature = 5 + bonus_season;
        }
        else
        {
            throw new IllegalArgumentException("Unknown DayTime: " + temp);
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
        update_rain();
        update_ensoleillement();
        update_humidite();
        update_temperature();
        extra_randomness();
        if(ensoleillement > 100) ensoleillement = 100;
    }

    private void extra_randomness()
    {
        ensoleillement += (Math.random() * ( 1.5 - (-1.5) ));
        temperature += (Math.random() * ( 1.5 - (-1.5) ));
        humidite += (Math.random() * ( 1.5 - (-1.5) ));
    }

    private int get_temperature_bonus_season(Saison s)
    {
        switch (s) {
            case HIVER:
                return 0;
            case AUTUMN : 
                return 5;
            case PRINTEMPS:
                return 7;
            case ETE :
                return 12;
            default:
                break;
        }
        return 0;
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
        return ensoleillement;
    }

    public void set_temperature(int t)
    {
        if(auto_simulate)
        {
            throw new IllegalAccessError("auto simulation must be turned off");
        }
        temperature = t;
    }
    public void set_humidite(int t)
    {
        if(auto_simulate)
        {
            throw new IllegalAccessError("auto simulation must be turned off");
        }
        humidite = t;
    }
    public void set_ensoleillement(int t)
    {
        if(auto_simulate)
        {
            throw new IllegalAccessError("auto simulation must be turned off");
        }
        ensoleillement = t;
    }

    public boolean is_raining()
    {
        return pluit;
    }

    public void set_rain()
    {
        if(pluit)
        {
            pluit = false;
            return;
        }
        pluit = true;
    }

}
