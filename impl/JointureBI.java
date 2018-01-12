package impl;

import Util.Join;
import operateurs.Jointure;
import stockage.Nuplet;

import java.util.Arrays;

public class JointureBI implements Jointure {
    @Override
    public Nuplet[] jointure(Nuplet[] t1, Nuplet[] t2, int att1, int att2) {
        Nuplet[] tab = new Nuplet[t1.length];
        Join util = new Join();
        int records = 0;
        for (int i = 0; i < t1.length; i++) {
            for (int j = 0; j < t2.length; j++) {
                if (t1[i].getAtt(att1).equals(t2[j].getAtt(att2))){
                    tab[i] = util.joinNuplets(t1[i],t2[j], att2);
                    records++;
                }
            }
        }
        return Arrays.copyOfRange(tab, 0, records);
    }
}
