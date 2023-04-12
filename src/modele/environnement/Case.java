
package modele.environnement;

import modele.Subscriber;

public abstract class Case implements Subscriber
{
    int humidite;
    public Case(int _humidite) 
    {
      humidite = _humidite;
    }

}
