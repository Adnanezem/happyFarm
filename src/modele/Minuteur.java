package modele;

import java.util.Vector;
import static java.lang.Thread.*;

public class Minuteur implements Runnable
{
    private Vector<Subscriber> subscribers;
    private long time_step;
    private boolean pause;
    private boolean is_running = true;
    public int nb_subs;

    public Minuteur(long _time_step) 
    {
        nb_subs = 0;
        time_step = _time_step;
        pause = false;
        subscribers = new Vector<Subscriber>();
        
    }
    public void start()
    {
        new Thread(this).start();
    }
    private void notify_subscribers()
    {
        for(Subscriber s : subscribers)
        {
            s.update();
        }
    }

    
    public void speed_up()
    {
        time_step /= 2;
    }


    public void slow_down()
    {
        time_step *= 2;
    }


    public void pause()
    {
        pause = true;
    }

    public void stop_clock()
    {
        is_running = false;
    }

    public void add_subscriber(Subscriber s) 
    {
        subscribers.add(s);
        nb_subs++;
    }

    public boolean get_is_running()
    {
        return is_running;
    }

    @Override
    public void run() 
    {
        while(is_running) 
        {
            if(pause) continue;
            try 
            {
                sleep(time_step);
                notify_subscribers();
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
            
        }
    }
}
