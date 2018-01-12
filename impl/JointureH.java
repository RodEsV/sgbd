package impl;

import Util.Join;
import operateurs.Jointure;
import stockage.Nuplet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;

public class JointureH implements Jointure{

    public Hashtable<Byte, ArrayList<Nuplet>> doHashTable(Nuplet[] data, int att) {
        Hashtable<Byte, ArrayList<Nuplet>> hashTable = new Hashtable<>();
        MessageDigest hash = null;
        try {
            hash = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        for(int  i = 0; i < data.length; i++){
            hash.update(data[i].getAtt(att).toString().getBytes());
            byte[] digest = hash.digest();
            if (hashTable.containsKey(digest[0]))
                hashTable.get(digest[0]).add(data[i]);
            else{
                int finalI = i;
                hashTable.put(digest[0], new ArrayList<Nuplet>(){{ add(data[finalI]);}});
            }
        }
        // x0 + 256 * x1 prendre deux elements pour la taille du hash
        //      (2⁸)
        return hashTable;
    }

    public Nuplet[] haching(Nuplet[] t1, Nuplet[] t2, int att1, int att2){
        Join util = new Join();
        ArrayList<Nuplet> join = new ArrayList<>();
        Hashtable<Byte, ArrayList<Nuplet>> hashTable = doHashTable(t1, att1);
        try {
            MessageDigest hash = MessageDigest.getInstance("MD5");
            for (int i = 0; i < t2.length ; i++){
                hash.update(t2[i].getAtt(att2).toString().getBytes());
                byte[] digest = hash.digest();
                ArrayList<Nuplet> temp = hashTable.get(digest[0]);
                if (temp != null){
                    for (Nuplet n: temp){
                        if (t2[i].getAtt(att2).equals(n.getAtt(att1))){
                            join.add(util.joinNuplets(n, t2[i], att2));
                        }
                    }
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Nuplet[] result = new Nuplet[join.size()];
        result = join.toArray(result);
        return result;
    }

    @Override
    public Nuplet[] jointure(Nuplet[] t1, Nuplet[] t2, int att1, int att2) {
        Nuplet[] result;
        if (t1.length > t2.length){
            result = haching(t1, t2, att1, att2);
        } else {
            result = haching(t2, t1, att2, att1);
        }
        return result;
    }

}
