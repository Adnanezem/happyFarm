package modele.environnement;

import modele.SimulateurPotager;

public class CaseNonCultivable extends Case
{
    public CaseNonCultivable()
    {
        humidite = 0;
    }
    public void update()
    {
        return;
    }
}
