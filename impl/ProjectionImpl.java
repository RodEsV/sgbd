package impl;

import operateurs.Projection;
import stockage.Nuplet;

import java.util.Arrays;

public class ProjectionImpl implements Projection {
    @Override
    public Nuplet[] project(Nuplet[] t, int[] atts) {
        // TODO Commenter pourquoi on a changé l'interface
        int index = 0;
        Nuplet[] set = new Nuplet[t.length];
        for (;index < t.length;){
            byte[] aux = new byte[atts.length];
            for (int j  = 0; j < atts.length; j++){
                aux[j] = (byte) t[index].getAtt(atts[j]);
            }
            set[index++] = new NupletInt(aux);
        }
        return Arrays.copyOfRange(set, 0,  index);
    }
}
