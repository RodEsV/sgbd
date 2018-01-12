package test;

import operateurs.Projection;
import stockage.*;
import impl.*;

public class Main {

    public static final int datasetSize = 100;
    public static final int nupletSize = 10;

    public static void main(String[] args) {

        // G?n?ration des donn?es
        Nuplet[] tab = new NupletInt[datasetSize];
        for (int i = 0; i < datasetSize; i++) {
            tab[i] = new NupletInt(nupletSize);
            for (int j = 0; j < nupletSize; j++) {
                tab[i].putAtt(j, (byte) (j + i));
            }
        }

        // Impl?mentation avec Table

        System.out.println("##############CREATION######################");
        System.out.println("Cr?ation d'une table");
        Table t = new TableInt("./table", nupletSize);
        for (int i = 0; i < datasetSize; i++) {
            t.put(tab[i]);
        }
        System.out.println("############################################\n");

        System.out.println("#############LECTURE########################");
        System.out.println("Lecture d'une table");
        for (int i = 0; i < datasetSize; i++) {
            System.out.println(t.get(i).toString());
        }
        System.out.println("############################################\n");

        System.out.println("#############ACTUALISATION##################");
        System.out.println("Nuplet 46 avant actualisation");
        System.out.println(t.get(46));

        t.update(t.get(46), 4, (byte) 50, (byte) 100);

        System.out.println("Nuplet 46 apres actualisation");
        System.out.println(t.get(46));
        System.out.println("############################################\n");
        System.out.println("#############SUPPRESSION####################");
        System.out.println("Nuplet 46 avant suppression");
        System.out.println(t.get(46));

        t.delete(t.get(46), 4, (byte)100);

        System.out.println("Nuplet 46 apres suppression");
        System.out.println(t.get(46));
        System.out.println("############################################\n");


        System.out.println("##############RESTRICTION###################");
        System.out.println("#################EGALITE####################");
        RestrictionInt ri = new RestrictionInt();
        Nuplet[] result = ri.egalite(tab, 0, (byte)0);
        for (Nuplet n: result)
            System.out.println(n.toString());
        System.out.println("############################################\n");
        System.out.println("#################SUPERIORITE################");
        result = ri.superieur(tab, 0, (byte)90);
        for (Nuplet n: result)
            System.out.println(n.toString());
        System.out.println("############################################\n");

        System.out.println("#################INFERIORITE################");
        result = ri.inferieur(tab, 0, (byte)10);
        for (Nuplet n: result)
            System.out.println(n.toString());
        System.out.println("############################################\n");

        System.out.println("###############PROJECTION##################");
        ProjectionImpl pro = new ProjectionImpl();
        result = pro.project(tab, new int[]{1,2,3});
        for (Nuplet n: result)
            System.out.println(n.toString());
        System.out.println("############################################\n");

        System.out.println("###############JOINTURE BI##################");
        JointureBI jbi = new JointureBI();
        result = jbi.jointure(tab, tab,5,0);
        Nuplet[] resultBI = result;
        for (Nuplet n: result)
            System.out.println(n.toString());
        System.out.println("############################################\n");


        System.out.println("###############JOINTURE H###################");
        JointureH jh = new JointureH();
        result = jh.jointure(tab, tab,5,0);
        for (Nuplet n: result)
            System.out.println(n.toString());
        System.out.println("############################################\n");
    }
}

