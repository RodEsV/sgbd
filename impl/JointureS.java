package impl;

import Util.Join;
import operateurs.Jointure;
import stockage.Nuplet;

import java.util.ArrayList;

public class JointureS implements Jointure{
    @Override
    public Nuplet[] jointure(Nuplet[] t1, Nuplet[] t2, int att1, int att2) {
        Join joinUtil = new Join();
        ArrayList<Nuplet> join = new ArrayList<>();
        joinUtil.sort((NupletInt[]) t1, att1, 0, t1.length-1);
        joinUtil.sort((NupletInt[]) t2, att2, 0, t2.length-1);
        int i = 0, j = 0;
        while (i < t1.length && j < t2.length){
            Byte dataLeft = (byte)t1[i].getAtt(att1);
            Byte dataRight = (byte)t2[j].getAtt(att2);
            if (dataLeft.equals(dataRight)){
                join.add(joinUtil.joinNuplets(t1[i++], t2[j++], att2));
            } else if(dataLeft < dataRight){
                i++;
            } else{
                j++;
            }
        }
        Nuplet[] joinNuplet = new Nuplet[join.size()];
        return join.toArray(joinNuplet);
    }
    public static void main(String[] args) {
        int datasetSize = 8;
        int nupletSize = 3;
        NupletInt[] tab = new NupletInt[datasetSize];
        for (int i = 0; i < datasetSize; i++) {
            tab[i] = new NupletInt(nupletSize);
            for (int j = 0; j < nupletSize; j++) {
                tab[i].putAtt(j, (byte) (j + Math.random()*3));
            }
        }

        NupletInt[] tab2 = new NupletInt[datasetSize];
        for (int i = 0; i < datasetSize; i++) {
            tab2[i] = new NupletInt(nupletSize);
            for (int j = 0; j < nupletSize; j++) {
                tab2[i].putAtt(j, (byte) (j + i));
            }
        }

        System.out.println("***Table 1***");
        for (Nuplet a : tab){
            System.out.println(a);
        }

        System.out.println("***Table 2***");
        for (Nuplet a : tab){
            System.out.println(a);
        }

        JointureS js = new JointureS();
        Nuplet[] result = js.jointure(tab, tab2, 0,2);

        System.out.println("***Result***");
        for (Nuplet a : result){
            System.out.println(a);
        }
    }
}
