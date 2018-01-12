package Util;

import impl.NupletInt;
import stockage.Nuplet;

public class Join {
    public Nuplet joinNuplets(Nuplet a, Nuplet b, int att){
        byte[] aux = new byte[a.size()+b.size()-1];
        int items = 0;
        for (int i = 0; i < a.size(); i++) {
            aux[i] = (byte)a.getAtt(i);
            items++;
        }
        for (int j = 0; j < b.size(); j++) {
            if (j != att) {
                aux[items] = (byte) b.getAtt(j);
                items++;
            }
        }
        return new NupletInt(aux);
    }
}
