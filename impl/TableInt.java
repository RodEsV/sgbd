package impl;

import java.util.Vector;

import stockage.Nuplet;
import stockage.Table;

public class TableInt implements Table {

    private int records;
    FichierInt f;

    public TableInt(String filePath, int nupletSize) {
        this.records = 0;
        this.f = new FichierInt(filePath, nupletSize);
    }

    @Override
    public Nuplet get(int pos) {
        return (Nuplet) f.get(pos);
    }

    @Override
    public int size() {
        return this.records;
    }

    @Override
    public void put(Nuplet n) {
        f.store(this.records, n);
        this.records++;
    }

    /**
     * Cette m�thode peut �tre am�lior�e par un index !
     */
    @Override
    public Nuplet[] getByAtt(int att, Object value) {
        Vector<Nuplet> v = new Vector<Nuplet>();
        for (int i = 0; i < this.size(); i++) {
            Nuplet temp = this.get(i);
            if ((byte) (temp.getAtt(att)) == (byte) value) {
                v.addElement(temp);
            }
        }
        Nuplet[] ret = new Nuplet[v.size()];
        for (int i = 0; i < v.size(); i++)
            ret[i] = v.elementAt(i);
        return ret;
    }

    @Override
    public Nuplet[] fullScan() {
        Nuplet[] fullScan = new Nuplet[]{};
        for (int i = 0; i < f.getCurrentSize(); i++)
            fullScan[i] = (Nuplet) f.get(i);
        return fullScan;
    }

    @Override
    public void insert(Nuplet n) {
        f.store(this.records++, n);
    }

    // TODO Check if the answer is correct
    @Override
    public void delete(Nuplet n, int att, Object value) {
        for (int i = 0; i < this.size(); i++) {
            n = this.get(i);
            if ((byte) (n.getAtt(att)) == (byte) value) {
                this.f.store(i, new NupletInt(n.size()));
            }
        }
    }

    @Override
    public void update(Nuplet n, int att, Object oldValue, Object newValue) {
        for (int i = 0; i < this.size(); i++) {
            n = this.get(i);
            if (((byte) (n.getAtt(att)) == (byte) oldValue)) {
                n.putAtt(att, newValue);
                this.f.store(i, n);
            }
        }
    }

}
