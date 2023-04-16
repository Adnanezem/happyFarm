package modele;

import java.util.Vector;

import modele.environnement.plantes.Plante;
import modele.environnement.plantes.Varietes;

public class Box
    {
        Vector<Plante> plantes;
        Varietes variete;
        int quantite;
        public Box()
        {
            plantes = new Vector<Plante>();
            quantite = 0;
        }

        public float get_prix()
        {
            float temp = 0;
            for (Plante p : plantes) 
            {
                temp += p.get_prix();
            }
            return temp;
        }
        public void add(Plante p)
        {
            assert quantite <= 5;
            plantes.add(p);
            quantite ++;
            if(quantite == 1)
            {
                variete = p.getVariete();
            }
        }
        public int get_quantite()
        {
            return quantite;
        }
        public Varietes get_variete()
        {
            return variete;
        }
        public boolean is_full()
        {
            if(quantite > 5) return false;
            return true;
        }
    }
