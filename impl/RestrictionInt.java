package impl;

import operateurs.Restriction;
import stockage.Nuplet;

import java.util.Arrays;


public class RestrictionInt implements Restriction {

    @Override
    public Nuplet[] egalite(Nuplet[] t, int att, Object v) {
        Nuplet[] egals = new Nuplet[t.length];
        int index = 0;
        for (int i = 0 ; i < t.length; i++){
            if ((byte)t[i].getAtt(att) == (byte) v){
                egals[index++] = t[i];
            }
        }
        return Arrays.copyOfRange(egals,0, index);
    }

    @Override
    public Nuplet[] superieur(Nuplet[] t, int att, Object v) {
        Nuplet[] sup = new Nuplet[t.length];
        int index = 0;
        for (int i = 0 ; i < t.length; i++){
            if ((byte)t[i].getAtt(att) >= (byte) v){
                sup[index++] = t[i];
            }
        }
        return Arrays.copyOfRange(sup,0, index);
    }

    @Override
    public Nuplet[] inferieur(Nuplet[] t, int att, Object v) {
        Nuplet[] inf = new Nuplet[t.length];
        int index = 0;
        for (int i = 0 ; i < t.length; i++){
            if ((byte)t[i].getAtt(att) <= (byte) v){
                inf[index++] = t[i];
            }
        }
        return Arrays.copyOfRange(inf,0, index);
    }
}
