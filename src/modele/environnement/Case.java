
package modele.environnement;

import modele.Subscriber;

public abstract class Case implements Subscriber
{
    protected int humidite;
    public Case() 
    {
      humidite = 0;
    }

}
