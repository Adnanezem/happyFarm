package modele;

import java.util.Vector;
import static java.lang.Thread.*;

public class Minuteur implements Runnable
{
    private Vector<Subscriber> subscribers;
    private long time_step;
    private boolean pause;

    public Minuteur(long _time_step) 
    {
        time_step = _time_step;
        pause = false;
        subscribers = new Vector<Subscriber>();
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

    public void add_subscriber(Subscriber s) {subscribers.add(s);}

    @Override
    public void run() 
    {
        while(true) 
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
