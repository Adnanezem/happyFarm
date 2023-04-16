package modele.meteo;
import modele.Subscriber;
public class Date implements Subscriber 
{
    //constants :
    private static int morning_time_winter = 8;
    private static int morning_time_spring = 6;
    private static int morning_time_summer = 4;
    private static int morning_time_autumn = 6;
    private static int noon_time_winter = 12;
    private static int noon_time_spring = 12;
    private static int noon_time_summer = 12;
    private static int noon_time_autumn = 12;
    private static int night_time_winter = 17;
    private static int night_time_spring = 19;
    private static int night_time_summer = 21;
    private static int night_time_autumn = 19;


    //class atributes : 
    private Saison season;
    private int day;
    private int heure;
    private int minute;
    public Date()
    {
        season = Saison.AUTUMN;
        day = 1;
        heure = 8;
        minute = 0;
    }

    public void update()
    {
        minute += 15;
        if(minute == 60)
        {
            minute = 0;
            heure += 1;
        }
        if(heure == 24)
        {
            heure = 0;
            day += 1;
        }
        if(day == 15)
        {
            day = 1;
            season = next_season();
        }
        
    }

    private Saison next_season()
    {
        if(season == Saison.HIVER)
        {
            return Saison.PRINTEMPS;
        }
        else if(season == Saison.PRINTEMPS)
        {
            return Saison.ETE;
        }
        else if(season == Saison.ETE)
        {
            return Saison.AUTUMN;
        }
        
        return Saison.HIVER;
        
    }

    public DayTime get_day_time()
    {
        if(season == Saison.HIVER)
        {
            return get_day_time_winter();
        }
        if(season == Saison.PRINTEMPS)
        {
            return get_day_time_spring();
        }
        if(season == Saison.ETE)
        {
            return get_day_time_summer();
        }
        if(season == Saison.AUTUMN)
        {
            return get_day_time_autumn();
        }
        return DayTime.NULL;
    }

    private DayTime get_day_time_winter()
    {
        if(heure < morning_time_winter)
        {
            return DayTime.NIGHT_TIME;
        }
        if(heure < noon_time_winter)
        {
            return DayTime.MORNING_TIME;
        }
        if(heure < night_time_winter)
        {
            return DayTime.NOON_TIME;
        }
        return DayTime.NULL;
    }
    private DayTime get_day_time_spring()
    {
        if(heure < morning_time_spring)
        {
            return DayTime.NIGHT_TIME;
        }
        if(heure < noon_time_spring)
        {
            return DayTime.MORNING_TIME;
        }
        if(heure < night_time_spring)
        {
            return DayTime.NOON_TIME;
        }
        return DayTime.NULL;
    }
    private DayTime get_day_time_summer()
    {
        if(heure < morning_time_summer)
        {
            return DayTime.NIGHT_TIME;
        }
        if(heure < noon_time_summer)
        {
            return DayTime.MORNING_TIME;
        }
        if(heure < night_time_summer)
        {
            return DayTime.NOON_TIME;
        }
        return DayTime.NULL;
    }
    private DayTime get_day_time_autumn()
    {
        if(heure < morning_time_autumn)
        {
            return DayTime.NIGHT_TIME;
        }
        if(heure < noon_time_autumn)
        {
            return DayTime.MORNING_TIME;
        }
        if(heure < night_time_autumn)
        {
            return DayTime.NOON_TIME;
        }
        return DayTime.NULL;
    }
}
