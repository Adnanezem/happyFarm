package modele;


import java.util.Vector;
import java.util.List;
import static java.lang.Thread.*;
import modele.environnement.Case;
import modele.Subscriber;

public class Minuteur implements Runnable
{
    private List<Subscriber> subscribers;
    private long time_step;
    private boolean pause;

    public void Minuteur(long _time_step) 
    {
        time_step = _time_step;
        pause = false;
        new Thread(this).start();
    }

    private void notify_subscribers()
    {
        for(Subscriber s : subscribers)
        {
            s.notify();
        }
    }

    public void pause()
    {
        pause = true;
    }

    public void add_subscriber(Subscriber s) {subscribers.add(s);}

    @Override
    public void run() 
    {
        while(!pause) 
        {
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
